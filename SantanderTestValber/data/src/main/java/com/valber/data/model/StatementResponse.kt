package com.valber.data.model

data class StatementResponse(val statementList: List<Statement>, val error: Error)

fun Statement.mapStatement(): Statement = Statement(title, desc, date, value)

fun List<Statement>.mapStatement(): List<Statement> = map { it.mapStatement() }