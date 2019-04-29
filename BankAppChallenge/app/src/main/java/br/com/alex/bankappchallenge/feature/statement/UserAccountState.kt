package br.com.alex.bankappchallenge.feature.statement

import br.com.alex.bankappchallenge.model.FormatedUserAccount

sealed class UserAccountStates {
    object LoadingUserAccount : UserAccountStates()
    data class UserAccountData(val userAccount: FormatedUserAccount) : UserAccountStates()
}

data class UserAccountState(
    val isLoadingUserAccount: Boolean = false,
    val userAccount: FormatedUserAccount? = null
)