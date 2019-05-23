package com.example.testesantander.domain.model

import com.google.gson.annotations.SerializedName

class UserData(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("bankAccount")
    val bankAccount: String,
    @SerializedName("agency")
    val agency: String,
    @SerializedName("balance")
    val balance: Double
    )