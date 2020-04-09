package pt.felipegouveia.bankapp.presentation.login.entity

import pt.felipegouveia.bankapp.data.login.model.UserAccount
import pt.felipegouveia.bankapp.presentation.entity.Error

data class LoginPresentation(
    val userAccount: UserAccount?,
    val error: Error?
)