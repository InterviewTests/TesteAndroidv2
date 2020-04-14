package pt.felipegouveia.bankapp.domain.model.statements

import pt.felipegouveia.bankapp.domain.model.common.Error

data class Statements(
    val statementList: List<Statement>?,
    val error: Error?
)