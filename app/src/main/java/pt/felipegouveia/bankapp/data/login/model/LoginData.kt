package pt.felipegouveia.bankapp.data.login.model

import pt.felipegouveia.bankapp.data.common.Error

data class LoginData(
    val userAccount: UserAccount?,
    val error: Error?
)