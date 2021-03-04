package com.example.accentureprojectbank

import com.google.gson.annotations.SerializedName

    data class UserAccountResponse(
    @SerializedName("userAccount")
    var usuario: User
)

    data class User(
    @SerializedName("userId")
    var idUsuario: Int,
    @SerializedName("name")
    var nome: String,
    @SerializedName("bankAccount")
    var contaBancaria: String,
    @SerializedName("agency")
    var agencia: String,
    @SerializedName("balance")
    var saldo: Double
)

    data class bodyLogin(
        @SerializedName("user")
        var usuarioOk: String,
        @SerializedName("password")
        var passwordOk: String
    )

