package com.example.data.model

import com.example.domain.entities.Statement
import com.google.gson.annotations.SerializedName


data class StatementData(
    @SerializedName("title")
    val titulo: String?,
    @SerializedName("desc")
    val descricao: String?,
    @SerializedName("date")
    val data: String?,
    @SerializedName("value")
    val valor: Double?
)

fun StatementData.toModel() =
    Statement(titulo = titulo, descricao = descricao, data = data, valor = valor)

