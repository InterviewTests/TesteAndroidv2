package com.example.ibm_test.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class StatementList(
    @SerializedName("statementList") val items: List<UserItemData>
)

data class UserItemData(
    @SerializedName("title") val title: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("date") val date: Date,
    @SerializedName("value") val value: Double
)