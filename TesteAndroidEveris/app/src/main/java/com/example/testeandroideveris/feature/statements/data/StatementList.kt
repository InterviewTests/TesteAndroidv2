package com.example.testeandroideveris.feature.statements.data

import com.squareup.moshi.Json

data class StatementList(
    @Json(name = "statementList")
    val statementList: List<StatementData>)