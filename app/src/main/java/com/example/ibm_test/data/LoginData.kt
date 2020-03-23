package com.example.ibm_test.data

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("user") val user: String,
    @SerializedName("password") val password: String
)