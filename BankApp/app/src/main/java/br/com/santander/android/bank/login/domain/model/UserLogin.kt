package br.com.santander.android.bank.login.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserLogin(
    @SerializedName("user")
    val user: String,

    @SerializedName("password")
    val password: String
): Serializable