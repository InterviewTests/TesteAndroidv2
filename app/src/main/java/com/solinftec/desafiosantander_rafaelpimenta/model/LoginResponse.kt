package com.solinftec.desafiosantander_rafaelpimenta.model

data class LoginResponse(
    val error: Map<String, Any>,
    val userAccount: UserAccount
)
