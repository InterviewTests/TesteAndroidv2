package com.gustavo.bankandroid.data.gson

import com.google.gson.annotations.SerializedName


data class StatementList(

    @SerializedName("title") val title: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("date") val date: String,
    @SerializedName("value") val value: Int
)