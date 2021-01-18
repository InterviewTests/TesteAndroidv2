package com.jeanjnap.data.model.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "status") val status: Int? = null,
    @Json(name = "error") val error: String? = null,
    @Json(name = "message") val errorMessage: String? = null
)
