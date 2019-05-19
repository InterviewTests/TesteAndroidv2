package br.com.teste.santander.login.presenter

import br.com.teste.santander.login.repository.model.UserAccount

interface LoginPresenter {
    fun setUser(user: String)
    fun showMessage(message: String)
    fun onLoginSuccess(userAccount: UserAccount)
    fun onLoginError(error: String)
}