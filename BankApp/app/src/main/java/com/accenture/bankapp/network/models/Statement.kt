package com.accenture.bankapp.network.models

import com.google.gson.annotations.SerializedName

data class Statement(
    @SerializedName("title")
    var title: String,
    @SerializedName("desc")
    var desc: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("value")
    var value: Float,
)