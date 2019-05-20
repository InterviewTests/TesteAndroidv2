package br.com.teste.santander.spy

import br.com.teste.santander.login.interactor.LoginInteractor
import br.com.teste.santander.login.view.LoginView
import br.com.teste.santander.model.UserAccount

class LoginViewSpy: LoginView {

    lateinit var interactor: LoginInteractor

    var onLoginSuccess = false
    var onLoginError = false

    override fun init() {
    }

    override fun setUser(user: String) {

    }

    override fun showMessage(message: String) {

    }

    override fun onLoginSuccess(userAccount: UserAccount) {
        onLoginSuccess = true
    }

    override fun onLoginError(error: String) {
        onLoginError = true
    }

}