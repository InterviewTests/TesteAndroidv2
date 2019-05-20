package br.com.teste.santander.spy

import br.com.teste.santander.login.presenter.LoginPresenter
import br.com.teste.santander.model.UserAccount

open class LoginPresenterSpy: LoginPresenter {

    var setUserCalled = false

    override fun setUser(user: String) {
        setUserCalled = true
    }

    override fun showMessage(message: String) {

    }

    override fun onLoginSuccess(userAccount: UserAccount) {

    }

    override fun onLoginError(error: String) {

    }

}