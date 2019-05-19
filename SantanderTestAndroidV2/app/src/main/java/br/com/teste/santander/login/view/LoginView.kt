package br.com.teste.santander.login.view

import br.com.teste.santander.model.UserAccount

interface LoginView {
    fun init()
    fun setUser(user: String)
    fun showMessage(message: String)
    fun onLoginSuccess(userAccount: UserAccount)
    fun onLoginError(error: String)
}