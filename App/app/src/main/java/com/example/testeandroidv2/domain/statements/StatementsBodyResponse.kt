package com.example.testeandroidv2.domain.statements

import com.example.testeandroidv2.domain.statements.ErrorStatement
import com.example.testeandroidv2.domain.statements.Statement
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatementsBodyResponse(
    @Json(name = "error")
    val error: ErrorStatement,
    @Json(name = "statementList")
    val statementList: List<Statement>
)