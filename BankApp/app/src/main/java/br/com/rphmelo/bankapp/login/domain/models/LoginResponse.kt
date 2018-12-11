package br.com.rphmelo.bankapp.login.domain.models

data class LoginResponse(val userAccount: UserAccount, val error: LoginError)