package com.example.santantest.model

data class LoginResponse(
    val userAccount: UserAccount,
    val error: APIError
)