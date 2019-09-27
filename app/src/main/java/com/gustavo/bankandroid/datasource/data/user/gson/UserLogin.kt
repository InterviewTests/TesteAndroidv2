package com.gustavo.bankandroid.datasource.data.user.gson

import com.google.gson.annotations.SerializedName

data class UserLogin(

    @SerializedName("user") val user: String,
    @SerializedName("password") val password: String
)