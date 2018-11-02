package com.ygorcesar.testeandroidv2.base.common.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseError(
    @Json(name = "code") val code: Int? = 0,
    @Json(name = "message") val message: String? = ""
)