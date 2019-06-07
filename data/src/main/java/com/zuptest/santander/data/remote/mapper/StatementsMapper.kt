package com.zuptest.santander.data.remote.mapper

import com.zuptest.santander.data.remote.response.StatementsResponse
import com.zuptest.santander.domain.business.model.Money
import com.zuptest.santander.domain.business.model.Statement
import java.util.*

fun StatementsResponse.mapToEntity(): List<Statement> {
    return this.statements.map {
        Statement(
            title = it.title,
            description = it.description,
            amount = Money(it.amount),
            date = Date(it.date)
        )
    }
}