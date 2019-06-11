package com.zuptest.santander.statement

import com.zuptest.santander.data.remote.mapper.mapToEntity
import com.zuptest.santander.data.remote.response.AccountResponse
import com.zuptest.santander.data.remote.response.StatementResponse
import com.zuptest.santander.data.remote.response.StatementsResponse

const val USER_ID = 1

val ACCOUNT = AccountResponse(
    userId = 1,
    userName = "José Teste da Silva Sauro",
    balance = 1000.0,
    bankAccount = "0987654321",
    bankAgency = "0001"
).mapToEntity()


val STATEMENTS_RESPONSE = StatementsResponse(
    listOf(
        StatementResponse(
            title = "Debito",
            description = "Conta de luz",
            amount = 100.0,
            date = "2019-06-08"
        ),
        StatementResponse(
            title = "Crédito",
            description = "Gustavo Lima",
            amount = 1000.0,
            date = "2019-05-08"
        )
    )
).mapToEntity()