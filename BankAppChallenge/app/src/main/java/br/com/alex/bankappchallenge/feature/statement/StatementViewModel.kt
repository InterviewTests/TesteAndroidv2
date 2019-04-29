package br.com.alex.bankappchallenge.feature.statement

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.OnLifecycleEvent
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
    private val statementReducerContract: StatementReducerContract,
    private val userAccountReducerContract: UserAccountReducerContract
) : ViewModel(), StatementInteractorOutput, LifecycleObserver {

    private val disposables = CompositeDisposable()
    private val intentions = PublishSubject.create<StatementIntentions>()

    private val _stateStatement = MutableLiveData<StatementState>()
    val statesStatement: LiveData<StatementState>
        get() = _stateStatement

    private val _stateUserAccount = MutableLiveData<UserAccountState>()
    val stateUserAccount: LiveData<UserAccountState>
        get() = _stateUserAccount

    private val _navigations = MutableLiveData<SingleEvent<StatementNavigation>>()
    val navigations: LiveData<SingleEvent<StatementNavigation>>
        get() = _navigations

    init {
        _stateStatement.postValue(StatementState())
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
                _stateStatement.value,
                StatementStates.StatementListState(formatedStatementList)
            ).run {
                _stateStatement.postValue(this)
            }
    }

    override fun loadingStatements() {
        statementReducerContract
            .reducer(
                _stateStatement.value,
                StatementStates.LoadingStatement
            ).run {
                _stateStatement.postValue(this)
            }
    }

    override fun loadingUserAccount() {
        userAccountReducerContract
            .reducer(
                UserAccountState(),
                UserAccountStates.LoadingUserAccount
            ).run {
                _stateUserAccount.postValue(this)
            }
    }

    override fun logout() {
        _navigations.postValue(SingleEvent(StatementNavigation.NavigateBackToLogin))
    }

    override fun userAccountData(formatedUserAccount: FormatedUserAccount) {
        userAccountReducerContract
            .reducer(
                UserAccountState(),
                UserAccountStates.UserAccountData(formatedUserAccount)
            ).run {
                _stateUserAccount.postValue(this)
            }
    }

    override fun loadingStatementError(errorMessage: String) {
        statementReducerContract
            .reducer(
                _stateStatement.value,
                StatementStates.Error(errorMessage)
            ).run {
                _stateStatement.postValue(this)
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