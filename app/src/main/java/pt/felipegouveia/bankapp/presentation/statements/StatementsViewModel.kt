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

    private val _userId: MutableLiveData<Int> = MutableLiveData()
    val userId: LiveData<Int> = _userId

    private val _statements: MutableLiveData<Response<StatementsPresentation>> = MutableLiveData()
    val statements: LiveData<Response<StatementsPresentation>> = _userId.switchMap {
        LiveDataReactiveStreams.fromPublisher(fetchStatements())
    }

    private val _mutableProgressbar = MutableLiveData<Int>().apply { View.GONE }
    val mutableProgressbar: LiveData<Int> = _mutableProgressbar

    private fun fetchStatements(): Flowable<Response<StatementsPresentation>>{

        val flowableRequest = useCase.getStatements(checkNotNull(userAccount.value?.userId))
            .flatMap { Flowable.just(mapper.toResponse(mapper.mapFrom(it))) }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .onErrorReturnItem(Response(status = Status.ERROR, error = Error("Error while fetching statements")))

        val disposable = flowableRequest
            .doOnSubscribe{ onFetchStarted() }
            .doFinally{ onFetchFinished() }
            .subscribe()

        add(disposable)

        return flowableRequest
    }

    fun setUserAccount(userAccount: UserAccountPresentation?){
        val update = userAccount
        if (_userAccount.value == update || update == null) {
            return
        }
        _userId.value = update.userId
        _userAccount.value = update
    }

    fun retry(){
        val userId = userId.value
        _userId.value = userId
    }

    private fun onFetchStarted(){
        if(BuildConfig.DEBUG) EspressoIdlingResource.increment()
        _mutableProgressbar.value = View.VISIBLE
    }

    private fun onFetchFinished(){
        if(BuildConfig.DEBUG) EspressoIdlingResource.decrement()
        _mutableProgressbar.value = View.GONE
    }

}