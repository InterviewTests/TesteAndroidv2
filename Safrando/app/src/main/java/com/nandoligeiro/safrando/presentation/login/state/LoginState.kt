package com.nandoligeiro.safrando.presentation.login.state


sealed class LoginState<out T> {
    data class Success<T>(val data: T) : LoginState<T>()
    object Default: LoginState<Nothing>()
    object Error: LoginState<Nothing>()
    object CheckFieldError: LoginState<Nothing>()
}
