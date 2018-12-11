package br.com.rphmelo.bankapp.login.presentation

import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.domain.models.LoginResponse
import br.com.rphmelo.bankapp.login.domain.interactor.LoginInteractor
import br.com.rphmelo.bankapp.login.domain.models.LoginView
import br.com.rphmelo.bankapp.login.domain.models.OnLoginFinishedListener

class LoginPresenter(var loginView: LoginView?, val loginInteractor: LoginInteractor) :
        OnLoginFinishedListener {

    fun login(loginRequest: LoginRequest) {
        loginView?.showProgress()
        loginInteractor.login(loginRequest, this)
    }

    fun onDestroy() {
        loginView = null
    }

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
}