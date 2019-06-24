package br.com.vinicius.bankapp.data.remote.model


data class LoginResponse(
    val error: ErrorResponse,
    val userAccount: LoginModel
)