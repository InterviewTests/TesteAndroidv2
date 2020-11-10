package com.example.data.model

import com.example.domain.entidades.ContaUsuario
import com.google.gson.annotations.SerializedName

data class ContaUsuarioData(
    @SerializedName("userId")
    val id: Int?,
    @SerializedName("name")
    val nome: String?,
    @SerializedName("bankAccount")
    val conta: String?,
    @SerializedName("agency")
    val agencia: String?,
    @SerializedName("balance")
    val saldo: Double?
)

fun ContaUsuarioData.converterParaContaUsuario() =
    ContaUsuario(id = id, nome = nome, conta = conta, agencia = agencia, saldo = saldo)