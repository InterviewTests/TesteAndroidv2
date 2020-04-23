package com.tata.bank.model

data class LoginResponse(
    val error: Error,
    val userAccount: UserAccount
)