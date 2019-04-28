package br.com.alex.bankappchallenge.interactor

import br.com.alex.bankappchallenge.model.Login
import br.com.alex.bankappchallenge.network.model.LoginRequest
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import br.com.alex.bankappchallenge.utils.PasswordValidatorContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginInteractor(
    private val loginRepositoryContract: LoginRepositoryContract,
    private val passwordValidatorContract: PasswordValidatorContract
) : LoginInteractorContract {

    private val disposables = CompositeDisposable()
    private lateinit var loginInteractorOutput: LoginInteractorOutput

    override fun login(login: Login) {
        if (login.password.isNotEmpty() && login.user.isNotEmpty()) {
            if (passwordValidatorContract.validatePassword(login.password)) {

                val subscribe = loginRepositoryContract
                    .login(LoginRequest(login.user, login.password))
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { loginInteractorOutput.logginIn() }.toObservable()
                    .map {
                        if (it.error.code != 0L) {
                            loginInteractorOutput.loginError(it.error.message)
                        } else {
                            it.userAccount.let { user ->
                                loginRepositoryContract.saveUserLogin(login.user)
                                loginRepositoryContract.saveUserAccount(user)
                                loginInteractorOutput.loginSuccess()
                            }
                        }
                    }
                    .subscribe()

                disposables.add(subscribe)
            } else {
                loginInteractorOutput.passwordInvalid()
            }
        } else {
            if (login.user.isEmpty()) loginInteractorOutput.emptyUser()
            else loginInteractorOutput.emptyPassword()
        }
    }

    override fun hasUser() {
        val userLogin = loginRepositoryContract.getUserLogin()
        if (userLogin.isNotEmpty()) {
            loginInteractorOutput.hasUser(userLogin)
        }
    }

    override fun getUserAccount() = loginRepositoryContract.getUserAccount()

    override fun loginInteractorOutputImpl(loginInteractorOutput: LoginInteractorOutput) {
        this.loginInteractorOutput = loginInteractorOutput
    }

    override fun clear() {
        disposables.clear()
    }
}

interface LoginInteractorOutput {
    fun loginSuccess()
    fun loginError(errorMessage: String)
    fun passwordInvalid()
    fun logginIn()
    fun emptyUser()
    fun emptyPassword()
    fun hasUser(user: String)
}