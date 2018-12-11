package br.com.rphmelo.bankapp.presentation.login.models

import br.com.rphmelo.bankapp.models.LoginResponse

interface OnLoginFinishedListener {
    fun onUserEmptyError()
    fun onPasswordEmptyError()
    fun onUserInvalidError()
    fun onPasswordInvalidError()
    fun onLoginSuccess(loginResponse: LoginResponse)
    fun onLoginError()
}