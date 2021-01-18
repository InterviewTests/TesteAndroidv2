package com.jeanjnap.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class StatementResponse(
    @Json(name = "title") val title: String?,
    @Json(name = "desc") val desc: String?,
    @Json(name = "date") val date: String?,
    @Json(name = "value") val value: BigDecimal?
)
