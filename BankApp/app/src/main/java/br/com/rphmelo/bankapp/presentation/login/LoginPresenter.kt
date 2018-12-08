package br.com.rphmelo.bankapp.presentation.login

import br.com.rphmelo.bankapp.presentation.login.models.LoginView
import br.com.rphmelo.bankapp.presentation.login.models.OnLoginFinishedListener

class LoginPresenter(var loginView: LoginView?, val loginInteractor: LoginInteractor) :
        OnLoginFinishedListener {

    fun validateCredentials(user: String, password: String) {
        loginView?.showProgress()
        loginInteractor.login(user, password, this)
    }

    fun onDestroy() {
        loginView = null
    }

    override fun onUserError() {
        loginView?.apply {
            setUserError()
            hideProgress()
        }
    }

    override fun onPasswordError() {
        loginView?.apply {
            setPasswordError()
            hideProgress()
        }
    }

    override fun onSuccess() {
        loginView?.navigateToCurrencyPage()
    }
}