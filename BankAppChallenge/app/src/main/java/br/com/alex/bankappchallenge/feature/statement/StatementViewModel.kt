package br.com.alex.bankappchallenge.feature.statement

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import br.com.alex.bankappchallenge.interactor.StatementInteractorContract
import br.com.alex.bankappchallenge.interactor.StatementInteractorOutput
import br.com.alex.bankappchallenge.model.FormatedStatement
import br.com.alex.bankappchallenge.model.FormatedUserAccount
import br.com.alex.bankappchallenge.utils.SingleEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class StatementViewModel(
    private val statementInteractorContract: StatementInteractorContract,
    private val statementReducerContract: StatementReducerContract
) : ViewModel(), StatementInteractorOutput, LifecycleObserver {

    private val disposables = CompositeDisposable()
    private val intentions = PublishSubject.create<StatementIntentions>()

    private val _state = MutableLiveData<StatementState>()
    val states: LiveData<StatementState>
        get() = _state

    private val _navigations = MutableLiveData<SingleEvent<StatementNavigation>>()
    val navigations: LiveData<SingleEvent<StatementNavigation>>
        get() = _navigations

    init {
        _state.postValue(StatementState())
        statementInteractorContract.statementInteractorOutputImpl(this)

        disposables.add(
            Observable
                .merge(
                    fetchStatementsIntentionCall(),
                    fetchUserAccountIntentionCall(),
                    logoutIntentionCall()
                ).subscribe()
        )
    }

    fun execute(intention: StatementIntentions) {
        intentions.onNext(intention)
    }

    fun logoutIntentionCall() =
        intentions
            .ofType(StatementIntentions.Logout::class.java)
            .map {
                statementInteractorContract.logout()
            }

    fun fetchUserAccountIntentionCall() =
        intentions
            .ofType(StatementIntentions.FetchUserAccountData::class.java)
            .map {
                statementInteractorContract.fetchUserAccount()
            }

    fun fetchStatementsIntentionCall() =
        intentions
            .ofType(StatementIntentions.FetchStatements::class.java)
            .map {
                statementInteractorContract.fetchStatements()
            }

    override fun fetchStatementSuccess(formatedStatementList: List<FormatedStatement>) {
        statementReducerContract
            .reducer(
                _state.value,
                StatementStates.StatementListState(formatedStatementList)
            ).run {
                _state.postValue(this)
            }
    }

    override fun loadingStatements() {
        statementReducerContract
            .reducer(
                _state.value,
                StatementStates.LoadingStatement
            ).run {
                _state.postValue(this)
            }
    }

    override fun loadingUserAccount() {
        statementReducerContract
            .reducer(
                StatementState(),
                StatementStates.LoadingUserAccount
            ).run {
                _state.postValue(this)
            }
    }

    override fun logout() {
        _navigations.postValue(SingleEvent(StatementNavigation.NavigateBackToLogin))
    }

    override fun userAccountData(formatedUserAccount: FormatedUserAccount) {
        statementReducerContract
            .reducer(
                StatementState(),
                StatementStates.UserAccountState(formatedUserAccount)
            ).run {
                _state.postValue(this)
            }
    }

    override fun loadingStatementError(errorMessage: String) {
        statementReducerContract
            .reducer(
                _state.value,
                StatementStates.Error(errorMessage)
            ).run {
                _state.postValue(this)
            }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchData() {
        execute(StatementIntentions.FetchUserAccountData)
        execute(StatementIntentions.FetchStatements)
    }

    override fun onCleared() {
        statementInteractorContract.clear()
        disposables.clear()
        intentions.onComplete()
        super.onCleared()
    }
}