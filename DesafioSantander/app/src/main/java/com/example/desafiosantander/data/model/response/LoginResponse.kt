package com.example.desafiosantander.data.model.response

import com.example.desafiosantander.data.model.basic.UserAccount

data class LoginResponse(
    val userAccount: UserAccount? = null,
    val error: Error? = null
)