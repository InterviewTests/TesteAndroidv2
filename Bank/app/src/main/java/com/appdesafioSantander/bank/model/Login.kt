package com.appdesafioSantander.bank.model

data class Login(
    val userAccount: UserAccount,
    val error: Map<String, Any>
)