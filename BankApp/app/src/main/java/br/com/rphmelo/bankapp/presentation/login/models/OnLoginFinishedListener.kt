package br.com.rphmelo.bankapp.presentation.login.models

interface OnLoginFinishedListener {
    fun onUserError()
    fun onPasswordError()
    fun onSuccess()
}