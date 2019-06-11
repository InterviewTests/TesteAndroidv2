package com.zuptest.santander.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("user") val login: String,
    @SerializedName("password") val password: String
)