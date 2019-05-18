package br.com.teste.santander.login.interactor

interface LoginInteractor {
    fun doLogin(user: String, password: String)
}