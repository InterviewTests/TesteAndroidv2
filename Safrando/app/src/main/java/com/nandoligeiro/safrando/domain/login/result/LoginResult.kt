package com.nandoligeiro.safrando.domain.login.result

sealed class LoginResult<out T> {
    data class Success<T>(val data: T) : LoginResult<T>()
    data class Error(val t: Throwable) : LoginResult<Nothing>()
    object CheckFieldError : LoginResult<Nothing>()
}
