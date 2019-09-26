package com.gustavo.bankandroid.features.login.data.gson

import com.google.gson.annotations.SerializedName

data class UserLogin(

    @SerializedName("user") val user: String,
    @SerializedName("password") val password: String
)