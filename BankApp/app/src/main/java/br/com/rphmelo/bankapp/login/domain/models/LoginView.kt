package br.com.rphmelo.bankapp.login.domain.models

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