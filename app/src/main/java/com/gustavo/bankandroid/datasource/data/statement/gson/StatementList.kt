package com.gustavo.bankandroid.datasource.data.statement.gson

import com.google.gson.annotations.SerializedName


data class StatementList(

    @field:SerializedName("title") val title: String,
    @field:SerializedName("desc") val desc: String,
    @field:SerializedName("date") val date: String,
    @field:SerializedName("value") val value: Double
)