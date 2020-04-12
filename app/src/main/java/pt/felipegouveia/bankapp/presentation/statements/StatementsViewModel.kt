package pt.felipegouveia.bankapp.presentation.statements

import android.view.View
import androidx.lifecycle.*
import io.reactivex.Flowable
import pt.felipegouveia.bankapp.BuildConfig
import pt.felipegouveia.bankapp.domain.interactors.StatementsUseCase
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.base.BaseViewModel
import pt.felipegouveia.bankapp.presentation.entity.Error
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.entity.Status
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation
import pt.felipegouveia.bankapp.presentation.statements.entity.mapper.StatementsPresentationMapper
import pt.felipegouveia.bankapp.util.EspressoIdlingResource
import javax.inject.Inject

class StatementsViewModel @Inject constructor(
    private val schedulers: BaseSchedulerProvider,
    private val useCase: StatementsUseCase,
    private val mapper: StatementsPresentationMapper
) : BaseViewModel() {

    private val _userId: MutableLiveData<Int> = MutableLiveData()
    val userId: LiveData<Int> = _userId


    private val _statements: MutableLiveData<Response<StatementsPresentation>> = MutableLiveData()
    val statements: LiveData<Response<StatementsPresentation>> = _userId.switchMap {
        fetchStatements()
    }

    private val _mutableProgressbar = MutableLiveData<Int>()
    val mutableProgressbar: LiveData<Int> = _mutableProgressbar

    private fun fetchStatements(): LiveData<Response<StatementsPresentation>>{
        val listFlowable = useCase.getStatements(userId.value?: -1)
            .flatMap {it -> mapper.flowable(it) }
            .flatMap {
                if(it.error == null){
                    Flowable.just(
                        Response(
                            status = Status.ERROR,
                            error = it.error
                        )
                    )
                } else {
                    Flowable.just(
                        Response(
                            status = Status.SUCCESSFUL,
                            data = it
                        )
                    )
                }
            }

        val disposable = listFlowable
            .subscribeOn(schedulers.io())
            .doOnSubscribe { onFetchStarted() }
            .doOnNext { onFetchFinished() }
            .doOnError { onFetchFinished() }
            .observeOn(schedulers.ui())
            .subscribe()

        add(disposable)
        return LiveDataReactiveStreams.fromPublisher(listFlowable)
    }

    fun setUserId(userId: Int){
        val update = userId
        if (_userId.value == update) {
            return
        }
        _userId.value = update
    }

    fun retry(){
        val userId = userId.value
        if (userId != null) {
            _userId.value = userId
        }
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