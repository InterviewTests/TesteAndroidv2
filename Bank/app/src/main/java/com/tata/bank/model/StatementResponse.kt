package com.tata.bank.model

data class StatementResponse(
    val error: Error,
    val statementList: List<Statement>
)