package br.com.rms.bankapp.data.remote.model

import br.com.rms.bankapp.data.local.database.entity.Statement

data class StatementResponse(
    val statementList: MutableList<Statement>,
    val error: Error?
)