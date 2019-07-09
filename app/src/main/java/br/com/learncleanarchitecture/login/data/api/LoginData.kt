package br.com.learncleanarchitecture.login.data.api

import com.google.gson.annotations.SerializedName

data class LoginResult(val userAccount: LoginData, val error: Error)

data class Error(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("message")
    val message: String? = ""
)

data class LoginData(
    @SerializedName("userId")
    val userId: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("bankAccount")
    val bankAccount: String? = "",
    @SerializedName("agency")
    val agency: String? = "",
    @SerializedName("balance")
    val balance: Float? = 0F,

    internal var username: String? = "",
    internal var pass: String? = "",

    val error: Error?
)