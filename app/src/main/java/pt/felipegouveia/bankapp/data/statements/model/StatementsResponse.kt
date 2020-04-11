package pt.felipegouveia.bankapp.data.statements.model

import pt.felipegouveia.bankapp.data.common.Error

data class StatementsResponse(
    val statementList: List<Statement>?,
    val error: Error?
)