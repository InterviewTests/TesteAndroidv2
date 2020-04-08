package pt.felipegouveia.bankapp.data.login.model

import pt.felipegouveia.bankapp.data.common.Error

data class LoginResponse(
    val userAccount: UserAccount?,
    val error: Error?
)