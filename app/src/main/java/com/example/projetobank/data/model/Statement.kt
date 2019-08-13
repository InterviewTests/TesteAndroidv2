package com.example.projetobank.data.model

import com.google.gson.annotations.SerializedName

data class Statement(
    @SerializedName("date")
    val date: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("value")
    val value: Double
)