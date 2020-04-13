package com.example.testeandroidv2.domain.statements


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Statement(
    @Json(name = "date")
    val date: String,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "value")
    val value: Double
)