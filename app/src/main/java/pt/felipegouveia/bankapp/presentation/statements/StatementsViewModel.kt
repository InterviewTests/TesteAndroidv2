package pt.felipegouveia.bankapp.presentation.statements

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import io.reactivex.Flowable
import pt.felipegouveia.bankapp.BuildConfig
import pt.felipegouveia.bankapp.domain.interactors.StatementsUseCase
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.base.BaseViewModel
import pt.felipegouveia.bankapp.presentation.entity.Error
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.login.entity.UserAccountPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.mapper.StatementsPresentationMapper
import pt.felipegouveia.bankapp.util.EspressoIdlingResource
import javax.inject.Inject

class StatementsViewModel @Inject constructor(
    private val schedulers: BaseSchedulerProvider,
    private val useCase: StatementsUseCase,
    private val mapper: StatementsPresentationMapper
) : BaseViewModel() {

    private val _userAccount: MutableLiveData<UserAccountPresentation> = MutableLiveData()
    val userAccount: LiveData<UserAccountPresentation> = _userAccount

    private val _statements: MutableLiveData<Response<StatementsPresentation>> = MutableLiveData()
    val statements: LiveData<Response<StatementsPresentation>> = _userAccount.switchMap {
        LiveDataReactiveStreams.fromPublisher(fetchStatements2())
    }

    private val _mutableProgressbar = MutableLiveData<Int>()
    val mutableProgressbar: LiveData<Int> = _mutableProgressbar

    private fun fetchStatements2(): Flowable<Response<StatementsPresentation>>{

        val flowableResponse: Flowable<Response<StatementsPresentation>> = Flowable.just(null)
        val flowableList = useCase.getStatements(userAccount.value?.userId?: UNKNOWN_USER_ID)

        val disposable = flowableList
            .subscribeOn(schedulers.io())
            .doOnSubscribe { onFetchStarted() }
            .observeOn(schedulers.ui())
            .subscribe(
                { response ->
                    flowableResponse.apply {
                        Flowable.just(Response(status = Status.SUCCESSFUL, data = response))
                    }
                    onFetchFinished()
                },
                { error ->
                    flowableList.apply {
                        Flowable.just(Response(status = Status.ERROR, data = null, error = Error(error.message)))
                    }
                    onFetchFinished()
                })

        add(disposable)
        return flowableResponse
    }

    private fun fetchStatements(): LiveData<Response<StatementsPresentation>>{
        val listFlowable = useCase.getStatements(userAccount.value?.userId?: UNKNOWN_USER_ID)
            .flatMap {it -> mapper.flowable(it) }
            .flatMap {
                if(it.error == null){
                    Flowable.just(
                        Response(
                            status = Status.SUCCESSFUL,
                            data = it
                        )
                    )
                } else {
                    Flowable.just(
                        Response(
                            status = Status.ERROR,
                            error = it.error
                        )
                    )
                }
            } .subscribeOn(schedulers.io())
            .doOnSubscribe { onFetchStarted() }
            .doOnNext { onFetchFinished() }
            .doOnError { onFetchFinished() }
            .observeOn(schedulers.ui())

      //  val disposable = listFlowable

//            .subscribe()

       // add(disposable)
        return LiveDataReactiveStreams.fromPublisher(listFlowable)
    }

    fun setUserAccount(userAccount: UserAccountPresentation?){
        val update = userAccount
        if (_userAccount.value == update) {
            return
        }
        _userAccount.value = update
    }

    fun retry(){
        _statements.value = fetchStatements().value
    }

    fun logout(){
        fetchStatements()
    }

    private fun onFetchStarted(){
        if(BuildConfig.DEBUG) EspressoIdlingResource.increment()
        _mutableProgressbar.postValue(View.VISIBLE)
    }

    private fun onFetchFinished(){
        if(BuildConfig.DEBUG) EspressoIdlingResource.decrement()
        _mutableProgressbar.postValue(View.GONE)
    }

    companion object {
        const val UNKNOWN_USER_ID = -1
    }

}