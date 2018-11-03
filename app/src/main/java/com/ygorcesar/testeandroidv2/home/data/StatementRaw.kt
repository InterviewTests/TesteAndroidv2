package com.ygorcesar.testeandroidv2.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatementRaw(
    @Json(name = "title") var title: String?,
    @Json(name = "desc") var desc: String?,
    @Json(name = "date") var date: String?,
    @Json(name = "value") var value: Double?
)