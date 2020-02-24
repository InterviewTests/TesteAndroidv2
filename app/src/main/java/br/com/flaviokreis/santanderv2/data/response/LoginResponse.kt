package br.com.flaviokreis.santanderv2.data.response

import br.com.flaviokreis.santanderv2.data.model.Error
import br.com.flaviokreis.santanderv2.data.model.UserAccount

data class LoginResponse(
    val userAccount: UserAccount?,
    val error: Error?
)