package br.com.teste.santander.statements.repository.model

import br.com.teste.santander.model.Error
import br.com.teste.santander.model.Statement

data class StatementsResponse(
    val error: Error?,
    val statementList: List<Statement>?
)