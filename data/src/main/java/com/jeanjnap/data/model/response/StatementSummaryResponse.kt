package com.jeanjnap.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatementSummaryResponse(
    @Json(name = "statementList") val statementList: List<StatementResponse>?
)
