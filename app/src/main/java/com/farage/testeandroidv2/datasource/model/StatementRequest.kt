package com.farage.testeandroidv2.datasource.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StatementRequest(
    @SerializedName("statementList")
    @Expose
    val statementList: List<StatementList>
)

data class StatementList(
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("desc")
    @Expose
    val desc: String,
    @SerializedName("date")
    @Expose
    val date: String,
    @SerializedName("value")
    @Expose
    val value: Double
)