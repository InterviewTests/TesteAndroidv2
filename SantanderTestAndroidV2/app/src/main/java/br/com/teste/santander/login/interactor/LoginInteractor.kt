package br.com.teste.santander.login.interactor

import android.content.Context

interface LoginInteractor {
    fun verifyLastUser(context: Context)
    fun doLogin(context: Context, user: String, password: String)
}