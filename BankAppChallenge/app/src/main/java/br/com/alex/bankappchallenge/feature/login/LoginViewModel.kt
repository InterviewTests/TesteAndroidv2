package br.com.alex.bankappchallenge.feature.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import br.com.alex.bankappchallenge.interactor.LoginInteractorContract
import br.com.alex.bankappchallenge.interactor.LoginInteractorOutput
import br.com.alex.bankappchallenge.model.Login
import br.com.alex.bankappchallenge.utils.SingleEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class LoginViewModel(
    private val loginInteractorContract: LoginInteractorContract,
    private val loginReducerContract: LoginReducerContract
) : ViewModel(), LoginInteractorOutput, LifecycleObserver {

    private val disposables = CompositeDisposable()
    private val intentions = PublishSubject.create<LoginIntentions>()

    private val _state = MutableLiveData<LoginState>()
    val states: LiveData<LoginState>
        get() = _state

    private val _navigations = MutableLiveData<SingleEvent<LoginNavigation>>()
    val navigations: LiveData<SingleEvent<LoginNavigation>>
        get() = _navigations

    init {
        _state.postValue(LoginState())
        loginInteractorContract.loginInteractorOutputImpl(this)

        disposables.add(
            Observable
                .merge(
                    hasLoginIntentionCall(),
                    loginIntentionCall()
                ).subscribe()
        )
    }

    fun execute(intention: LoginIntentions) {
        intentions.onNext(intention)
    }

    private fun loginIntentionCall() =
        intentions
            .ofType(LoginIntentions.Login::class.java)
            .map {
                loginInteractorContract.login(Login(it.user, it.password))
            }

    private fun hasLoginIntentionCall() =
        intentions
            .ofType(LoginIntentions.HasUser::class.java)
            .map {
                loginInteractorContract.hasUser()
            }

    override fun loginSuccess() {
        _navigations.postValue(SingleEvent(LoginNavigation.NavigateToStatement))
    }

    override fun loginError(errorMessage: String) {
        loginReducerContract
            .reducer(
                _state.value,
                LoginStates.Error(errorMessage)
            ).run {
                _state.postValue(this)
            }
    }

    override fun passwordInvalid() {
        loginReducerContract
            .reducer(
                _state.value,
                LoginStates.PasswordInvalid
            ).run {
                _state.postValue(this)
            }
    }

    override fun logginIn() {
        loginReducerContract
            .reducer(
                _state.value,
                LoginStates.Loading
            ).run {
                _state.postValue(this)
            }
    }

    override fun emptyUser() {
        loginReducerContract
            .reducer(
                _state.value,
                LoginStates.EmptyUser
            ).run {
                _state.postValue(this)
            }
    }

    override fun emptyPassword() {
        loginReducerContract
            .reducer(
                _state.value,
                LoginStates.EmptyPassword
            ).run {
                _state.postValue(this)
            }
    }

    override fun hasUser(user: String) {
        loginReducerContract
            .reducer(
                LoginState(),
                LoginStates.HasUser(user)
            ).run {
                _state.postValue(this)
            }
    }

    override fun userInvalid() {
        loginReducerContract
            .reducer(
                _state.value,
                LoginStates.UserInvalid
            ).run {
                _state.postValue(this)
            }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun checkHasUser() {
        execute(LoginIntentions.HasUser)
    }

    override fun onCleared() {
        loginInteractorContract.clear()
        disposables.clear()
        intentions.onComplete()
        super.onCleared()
    }
}