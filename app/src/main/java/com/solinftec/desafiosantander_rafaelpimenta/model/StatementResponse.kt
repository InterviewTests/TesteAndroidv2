package com.solinftec.desafiosantander_rafaelpimenta.model

data class StatementResponse(
    val error: Error,
    val statementList: List<Statement>
)
