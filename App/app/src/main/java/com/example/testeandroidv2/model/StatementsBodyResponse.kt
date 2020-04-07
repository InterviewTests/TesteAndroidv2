package com.example.testeandroidv2.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatementsBodyResponse(
    @Json(name = "error")
    val error: Error,
    @Json(name = "statementList")
    val statementList: List<Statement>
)