package com.example.data.data

import com.example.domain.entidades.ListaStatements
import com.google.gson.annotations.SerializedName

data class ListaStatementsData(
    @SerializedName("statementList")
    val listaStatements: List<StatementData>?,
    @SerializedName("error")
    val erro: ErroData?
)


fun ListaStatementsData.converterParaListaStatements() =
    ListaStatements(
        listaStatements = listaStatements?.converterParaListaStatement(),
        erro = erro?.converterParaErro()
    )