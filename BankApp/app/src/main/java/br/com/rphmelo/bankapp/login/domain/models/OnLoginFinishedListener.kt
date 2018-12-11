package br.com.rphmelo.bankapp.login.domain.models

interface OnLoginFinishedListener {
    fun onUserEmptyError()
    fun onPasswordEmptyError()
    fun onUserInvalidError()
    fun onPasswordInvalidError()
    fun onLoginSuccess(loginResponse: LoginResponse)
    fun onLoginError()
}