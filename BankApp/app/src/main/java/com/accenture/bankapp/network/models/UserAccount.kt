package com.accenture.bankapp.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserAccount(
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("bankAccount")
    var bankAccount: String,
    @SerializedName("agency")
    var agency: String,
    @SerializedName("balance")
    var balance: Float,
) : Serializable