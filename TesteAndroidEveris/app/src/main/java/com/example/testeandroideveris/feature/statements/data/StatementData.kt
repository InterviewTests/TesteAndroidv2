package com.example.testeandroideveris.feature.statements.data

import com.example.testeandroideveris.feature.util.BankUtil.formatAmountValue
import com.example.testeandroideveris.feature.util.BankUtil.formatDate
import com.squareup.moshi.Json

data class StatementData(
    @Json(name = "title")
    val title: String,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "value")
    val value: Double
) {

    fun getFormattedValue() = formatAmountValue(value)

    fun getFormattedDate() = formatDate(date)
}