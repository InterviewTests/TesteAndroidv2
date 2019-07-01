package io.github.maikotrindade.bankapp.login

import io.github.maikotrindade.bankapp.login.model.LoginResponse
import io.github.maikotrindade.bankapp.login.model.User
import java.lang.ref.WeakReference

interface LoginInteractorInput {
    fun logIn(user: User)
    fun startLogin(user: User)
}

class LoginInteractor : LoginInteractorInput {

    lateinit var presenter: LoginPresenter
    private var worker: LoginWorker? = null

    override fun logIn(user: User) {
        presenter.logIn(user)
    }

    override fun startLogin(user: User) {
        if (worker == null) {
            worker = LoginWorker(WeakReference(this))
        }
        worker?.startLogin(user)
    }

    fun onLogInSuccess(loginResponse: LoginResponse?) {
        when {
            loginResponse?.userAccount?.name != null -> loginResponse.userAccount?.let {
                presenter.fragmentInput.get()?.navigateToStatementsScreen(it)
                worker?.saveToSharedPreferences(it)
            }
            loginResponse?.error?.code != null -> presenter.fragmentInput.get()?.showLoginError(loginResponse.error!!.message)
            else -> onLogInError()
        }
    }

    fun onLogInError() {
        presenter.fragmentInput.get()?.showLoginError()
    }

}