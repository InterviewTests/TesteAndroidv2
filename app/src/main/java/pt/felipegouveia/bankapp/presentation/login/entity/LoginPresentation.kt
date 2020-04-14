package pt.felipegouveia.bankapp.presentation.login.entity

import pt.felipegouveia.bankapp.presentation.entity.Error

data class LoginPresentation(
    val userAccount: UserAccountPresentation?,
    val error: Error?
)