package pt.felipegouveia.bankapp.domain.model.login

import pt.felipegouveia.bankapp.data.login.model.UserAccount
import pt.felipegouveia.bankapp.domain.model.common.Error

data class Login(
    val userAccount: UserAccount?,
    val error: Error?
)