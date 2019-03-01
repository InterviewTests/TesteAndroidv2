package com.example.androidtest.repository

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.util.*

class Statement(
    val title: String,
    val desc: String,
    val value: Double,
    val calendar: Calendar
) {
    constructor(raw: StatementRaw) : this(
        title = raw.title!!,
        desc = raw.desc!!,
        value = raw.value!!,
        calendar = Calendar.getInstance(Locale.getDefault()).apply {
            time = DateFormat.getDateInstance().parse(raw.date)
        }
    )
}


data class StatementRaw(
    @SerializedName("title") val title: String?,
    @SerializedName("desc") val desc: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("value") val value: Double?
)