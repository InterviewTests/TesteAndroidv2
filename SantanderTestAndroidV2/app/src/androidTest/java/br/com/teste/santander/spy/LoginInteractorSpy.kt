package br.com.teste.santander.spy

import android.content.Context
import br.com.teste.santander.login.interactor.LoginInteractor

class LoginInteractorSpy: LoginInteractor {

    var doLoginCalled = false
    var user = ""
    var password = ""

    var verifyLastUserCalled = false

    override fun verifyLastUser(context: Context) {
        verifyLastUserCalled = true
    }

    override fun doLogin(context: Context, user: String, password: String) {
        doLoginCalled = true
        this.user = user
        this.password = password
    }

}