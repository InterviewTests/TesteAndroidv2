package com.example.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequisicaoData(
    @SerializedName("user")
    val usuario: String?,
    @SerializedName("password")
    val senha: String
)