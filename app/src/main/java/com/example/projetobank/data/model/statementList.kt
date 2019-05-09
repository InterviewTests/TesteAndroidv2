package com.example.projetobank.data.model

import com.google.gson.annotations.SerializedName

data class statementList(
    @SerializedName("error")
    val error: Error,
    @SerializedName("statementList")
    val statementList: List<Statement>
)