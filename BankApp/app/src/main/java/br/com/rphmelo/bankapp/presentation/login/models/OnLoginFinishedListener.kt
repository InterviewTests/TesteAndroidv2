package br.com.rphmelo.bankapp.presentation.login.models

interface OnLoginFinishedListener {
    fun onUserEmptyError()
    fun onPasswordEmptyError()
    fun onUserInvalidError()
    fun onPasswordInvalidError()
    fun onSuccess()
}