package br.com.rphmelo.bankapp.presentation.login.models

import br.com.rphmelo.bankapp.models.LoginResponse

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setUserEmptyError()
    fun setPasswordEmptyError()
    fun setUserInvalidError()
    fun setPasswordInvalidError()
    fun onLoginSuccess(loginResponse: LoginResponse)
    fun onLoginError()
}