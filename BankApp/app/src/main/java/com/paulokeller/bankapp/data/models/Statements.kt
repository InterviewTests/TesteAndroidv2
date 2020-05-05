package com.paulokeller.bankapp.data.models

import com.google.gson.annotations.SerializedName


class Statement(
    @SerializedName("title") val title: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("date") val date: String,
    @SerializedName("value") val value: Double
)

class Statements(@SerializedName("statementList") val results: List<Statement>)