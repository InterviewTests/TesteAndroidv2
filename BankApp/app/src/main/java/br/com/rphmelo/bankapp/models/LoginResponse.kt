package br.com.rphmelo.bankapp.models

data class LoginResponse(val userAccount: UserAccount, val error: LoginError)