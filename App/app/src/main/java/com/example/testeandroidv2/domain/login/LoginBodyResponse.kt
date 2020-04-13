package com.example.testeandroidv2.domain.login


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginBodyResponse(
    @Json(name = "error")
    val error: ErrorLogin,
    @Json(name = "userAccount")
    val userAccount: UserAccount
)