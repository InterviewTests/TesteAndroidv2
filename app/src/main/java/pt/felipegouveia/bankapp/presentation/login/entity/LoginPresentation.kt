package pt.felipegouveia.bankapp.presentation.login.entity

import pt.felipegouveia.bankapp.data.common.Error
import pt.felipegouveia.bankapp.data.login.model.UserAccount

data class LoginPresentation(
    val userAccount: UserAccount?,
    val error: Error?
)