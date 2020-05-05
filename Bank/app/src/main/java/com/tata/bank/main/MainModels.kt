package com.tata.bank.main

import com.google.gson.annotations.SerializedName
import com.tata.bank.login.Error
import com.tata.bank.utils.dateFormat

data class Statement(
    @SerializedName("date") private val _date: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("title") val title: String,
    @SerializedName("value") val value: Double
) {
    val date: String
        get() = _date.dateFormat()
}

data class StatementResponse(
    val error: Error,
    val statementList: List<Statement>
)

data class AccountData(
    val name: String,
    val account: String,
    val balance:String
)