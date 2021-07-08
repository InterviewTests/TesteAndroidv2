package com.example.desafiosantander.data.model.response

import com.example.desafiosantander.data.model.basic.Statement

data class StatementResponse(
    val statementList: List<Statement>? = null,
    val error: Error? = null
)