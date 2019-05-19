package br.com.teste.santander.login.presenter

import br.com.teste.santander.model.UserAccount
import br.com.teste.santander.login.view.LoginView
import java.lang.ref.WeakReference

class LoginPresenterImpl: LoginPresenter {
    lateinit var loginView: WeakReference<LoginView>

    override fun setUser(user: String) {
        loginView.get()?.setUser(user)
    }

    override fun showMessage(message: String) {
        loginView.get()?.showMessage(message)
    }

    override fun onLoginSuccess(userAccount: UserAccount) {
        loginView.get()?.onLoginSuccess(userAccount)
    }

    override fun onLoginError(error: String) {
        loginView.get()?.onLoginError(error)
    }
}