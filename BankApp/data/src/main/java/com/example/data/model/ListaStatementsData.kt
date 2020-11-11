package com.example.data.model

import com.example.domain.entities.ListaStatements
import com.google.gson.annotations.SerializedName

data class ListaStatementsData(
    @SerializedName("statementList")
    val listaStatements: List<StatementData>?,
    @SerializedName("error")
    val erro: ErroData?
)


fun ListaStatementsData.toModel() =
    ListaStatements(
        listaStatements = listaStatements?.map { it.toModel() },
        erro = erro?.toModel()
    )