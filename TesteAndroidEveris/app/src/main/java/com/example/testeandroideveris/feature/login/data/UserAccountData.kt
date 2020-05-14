package com.example.testeandroideveris.feature.login.data

import com.squareup.moshi.Json

data class UserAccountData (
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "bankAccount")
    val bankAccount: String,
    @Json(name = "agency")
    val agency: String,
    @Json(name = "balance")
    val balance: Double)