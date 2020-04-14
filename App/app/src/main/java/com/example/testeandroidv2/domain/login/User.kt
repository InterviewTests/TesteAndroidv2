package com.example.testeandroidv2.domain.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "user")
    val user: String,
    @Json(name = "password")
    val password: String
)
