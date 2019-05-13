package com.example.projetobank.data.model

import com.google.gson.annotations.SerializedName

data class userAccount(
    @SerializedName("agency")
    val agency: String,
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("bankAccount")
    val bankAccount: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("userId")
    val userId: Int
)