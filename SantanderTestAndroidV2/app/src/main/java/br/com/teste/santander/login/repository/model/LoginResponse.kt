package br.com.teste.santander.login.repository.model

import br.com.teste.santander.model.Error
import br.com.teste.santander.model.UserAccount

data class LoginResponse(
    val error: Error?,
    val userAccount: UserAccount?
)