package br.com.rms.bankapp.data.model

import br.com.rms.bankapp.data.local.database.entity.Statement

data class StatementBody(
    val statementList: List<Statement?>?,
    val error: Error?
)