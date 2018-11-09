package br.com.santander.android.bank.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Login(
    @SerializedName("user")
    val user: String,

    @SerializedName("password")
    val password: String
): Serializable