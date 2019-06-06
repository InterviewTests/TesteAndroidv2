package com.zuptest.data.remote.mapper

import com.zuptest.data.remote.response.StatementsResponse
import com.zuptest.domain.business.model.Money
import com.zuptest.domain.business.model.Statement
import java.util.*

fun StatementsResponse.mapFrom(): List<Statement> {
    return this.statements.map {
        Statement(
            title = it.title,
            description = it.description,
            amount = Money(it.amount),
            date = Date(it.date)
        )
    }
}