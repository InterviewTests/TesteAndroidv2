package pt.felipegouveia.bankapp.presentation.statements.entity

import pt.felipegouveia.bankapp.presentation.entity.Error

data class StatementsPresentation(
    val statementList: List<StatementPresentation>?,
    val error: Error?
)