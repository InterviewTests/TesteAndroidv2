package com.example.data.data

import com.example.domain.entidades.Erro
import com.google.gson.annotations.SerializedName

data class ErroData(
    @SerializedName("code")
    val codigo: Int?,
    @SerializedName("message")
    val mensagem: String?
)

fun ErroData.converterParaErro() =
    Erro(codigo = codigo, mensagem = mensagem)