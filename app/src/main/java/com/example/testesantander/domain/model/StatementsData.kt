package com.example.testesantander.domain.model

import com.google.gson.annotations.SerializedName

class StatementsData(
    @SerializedName("title")
    val title: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("value")
    val value: Double
)