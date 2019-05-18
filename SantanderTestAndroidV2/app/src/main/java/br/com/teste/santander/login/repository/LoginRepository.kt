package br.com.teste.santander.login.repository

interface LoginRepository {
    fun doLogin(user: String, password: String)
}