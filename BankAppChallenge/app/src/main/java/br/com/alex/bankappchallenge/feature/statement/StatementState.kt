package br.com.alex.bankappchallenge.feature.statement

import br.com.alex.bankappchallenge.model.FormatedStatement
import br.com.alex.bankappchallenge.model.FormatedUserAccount

sealed class StatementStates {
    object LoadingStatement : StatementStates()
    object LoadingUserAccount : StatementStates()
    data class Error(val errorMessage: String) : StatementStates()
    data class StatementListState(val statementList: List<FormatedStatement>) : StatementStates()
    data class UserAccountState(val userAccount: FormatedUserAccount) : StatementStates()
}

data class StatementState(
    val isLoadingStatement: Boolean = false,
    val isLoadError: Boolean = false,
    val errorMessage: String = "",
    val isLoadingUserAccount: Boolean = false,
    val userAccount: FormatedUserAccount? = null,
    val statementList: List<FormatedStatement> = listOf()
)