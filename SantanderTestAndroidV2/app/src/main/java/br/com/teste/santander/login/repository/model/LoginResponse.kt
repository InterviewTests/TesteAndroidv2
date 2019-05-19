package br.com.teste.santander.login.repository.model

data class LoginResponse(
    val error: Error?,
    val userAccount: UserAccount?
)