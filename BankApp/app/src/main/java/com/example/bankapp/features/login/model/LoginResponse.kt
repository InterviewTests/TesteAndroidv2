package com.example.bankapp.features.login.model

data class LoginResponse(
    val userAccount: UserAccount?,
    val error: Errors?)
