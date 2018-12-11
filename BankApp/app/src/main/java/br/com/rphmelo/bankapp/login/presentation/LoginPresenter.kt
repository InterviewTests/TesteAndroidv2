package br.com.rphmelo.bankapp.login.presentation

import br.com.rphmelo.bankapp.login.domain.interactor.LoginInteractor
import br.com.rphmelo.bankapp.login.domain.models.*

class LoginPresenter(var loginView: LoginView?, val loginInteractor: LoginInteractor) :
        OnLoginFinishedListener {

    fun login(loginRequest: LoginRequest) {
        loginView?.showProgress()
        loginInteractor.login(loginRequest, this)
    }

    fun loadLoginSession() {
        loginView?.showProgress()
        loginInteractor.loadLoginSession(this)
    }

    fun onDestroy() {
        loginView = null
    }

    fun setLoginSession(login: LoginRequest) = loginInteractor.setLoginSession(login)

    override fun onUserEmptyError() {
        loginView?.apply {
            setUserEmptyError()
            hideProgress()
        }
    }

    override fun onPasswordEmptyError() {
        loginView?.apply {
            setPasswordEmptyError()
            hideProgress()
        }
    }

    override fun onUserInvalidError() {
        loginView?.apply {
            setUserInvalidError()
            hideProgress()
        }
    }

    override fun onPasswordInvalidError() {
        loginView?.apply {
            setPasswordInvalidError()
            hideProgress()
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        loginView?.hideProgress()
        loginView?.onLoginSuccess(loginResponse)
    }

    override fun onLoginError() {
        loginView?.hideProgress()
        loginView?.onLoginError()
    }

    override fun loadLoginSession(login: LoginRequest?) {
        loginView?.onLoadLoginSession(login)
        loginView?.hideProgress()
    }
}