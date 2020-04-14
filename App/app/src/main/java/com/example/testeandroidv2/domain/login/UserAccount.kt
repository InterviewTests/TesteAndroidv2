package com.example.testeandroidv2.domain.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAccount(
    @Json(name = "agency")
    val agency: String,
    @Json(name = "balance")
    val balance: Double,
    @Json(name = "bankAccount")
    val bankAccount: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "userId")
    val userId: Int
)