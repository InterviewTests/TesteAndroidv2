package com.lucianogiardino.bankapp.domain.model

import com.google.gson.annotations.SerializedName

data class Statement(

    @SerializedName("title")
    val title: String,

    @SerializedName("desc")
    val desc: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("value")
    val value: Double

)