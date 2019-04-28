package br.com.alex.bankappchallenge.feature.statement

import br.com.alex.bankappchallenge.model.FormatedStatement
import br.com.alex.bankappchallenge.model.FormatedUserAccount

sealed class StatementStates {
    object Loading : StatementStates()
    data class Error(val errorMessage: String) : StatementStates()
    data class StatementList(val statementList: List<FormatedStatement>) : StatementStates()
    data class UserAccount(val userAccount: FormatedUserAccount) : StatementStates()
}

data class StatementState(
    val isLoading: Boolean = false,
    val isLoadError: Boolean = false,
    val userAccount: FormatedUserAccount? = null,
    val statementList: List<FormatedStatement> = listOf()
)