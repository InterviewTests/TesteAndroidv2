package pt.felipegouveia.bankapp.domain.model

import pt.felipegouveia.bankapp.data.common.Error
import pt.felipegouveia.bankapp.data.login.model.UserAccount

data class Login(
    val userAccount: UserAccount?,
    val error: Error?
)