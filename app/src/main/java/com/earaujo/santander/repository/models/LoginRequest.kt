package com.earaujo.santander.repository.models

data class LoginRequest(
    var user: String,
    var password: String
)