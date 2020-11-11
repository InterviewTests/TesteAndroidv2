package com.example.data.model

import com.example.domain.entities.Erro
import com.google.gson.annotations.SerializedName

data class ErroData(
    @SerializedName("code")
    val codigo: Int?,
    @SerializedName("message")
    val mensagem: String?
)

fun ErroData.toModel() =
    Erro(codigo = codigo, mensagem = mensagem)