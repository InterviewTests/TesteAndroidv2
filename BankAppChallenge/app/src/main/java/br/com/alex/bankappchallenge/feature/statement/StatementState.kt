package br.com.alex.bankappchallenge.feature.statement

import br.com.alex.bankappchallenge.model.FormatedStatement

sealed class StatementStates {
    object LoadingStatement : StatementStates()
    data class Error(val errorMessage: String) : StatementStates()
    data class StatementListState(val statementList: List<FormatedStatement>) : StatementStates()
}

data class StatementState(
    val isLoadingStatement: Boolean = false,
    val isLoadError: Boolean = false,
    val errorMessage: String = "",
    val statementList: List<FormatedStatement> = listOf()
)