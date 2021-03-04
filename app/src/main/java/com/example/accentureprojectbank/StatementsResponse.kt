package com.example.accentureprojectbank

import com.google.gson.annotations.SerializedName

    data class StatementsResponse (
        @SerializedName("statementList")
        var inf: ArrayList<Informacoes>

)

    data class Informacoes(
        @SerializedName("title")
        var titulo: String,
        @SerializedName("desc")
        var descricao: String,
        @SerializedName("date")
        var data: String,
        @SerializedName("value")
        var valor: Double
    )
