package br.com.teste.santander.login.presenter

import br.com.teste.santander.login.view.LoginView
import java.lang.ref.WeakReference

class LoginPresenterImpl: LoginPresenter {
    lateinit var loginView: WeakReference<LoginView>

    override fun showMessage(message: String) {
        loginView.get()?.showMessage(message)
    }

}