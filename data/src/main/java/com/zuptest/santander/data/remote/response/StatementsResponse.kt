package com.zuptest.data.remote.response

import com.google.gson.annotations.SerializedName

data class StatementsResponse(
    @SerializedName("statementList") val statements: List<StatementResponse>
)

data class StatementResponse(
    @SerializedName("title") val title: String,
    @SerializedName("desc") val description: String,
    @SerializedName("date") val date: String,
    @SerializedName("value") val amount: Double
)