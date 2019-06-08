package com.zuptest.santander.data.remote.mapper

import com.zuptest.santander.data.remote.response.StatementsResponse
import com.zuptest.santander.domain.business.model.Money
import com.zuptest.santander.domain.business.model.Statement
import java.text.SimpleDateFormat
import java.util.*

fun StatementsResponse.mapToEntity(): List<Statement> {
    return this.statements.map {
        Statement(
            title = it.title,
            description = it.description,
            amount = Money(it.amount),

            date = parseDate(it.date)
        )
    }
}

private fun parseDate(date: String): Date {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
    return sdf.parse(date)
}