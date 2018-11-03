package com.ygorcesar.testeandroidv2.login.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAccount(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
)