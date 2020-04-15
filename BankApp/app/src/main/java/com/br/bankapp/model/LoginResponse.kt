package com.br.bankapp.model

data class LoginResponse(
    val userAccount: UserAccount,
    val error: Map<String, Any>
)