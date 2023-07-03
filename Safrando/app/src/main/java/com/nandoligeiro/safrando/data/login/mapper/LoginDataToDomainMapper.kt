package com.nandoligeiro.safrando.data.login.mapper

import com.nandoligeiro.safrando.data.login.model.LoginData
import com.nandoligeiro.safrando.domain.login.model.LoginDomain

class LoginDataToDomainMapper {
    fun toDomainLogin(login: LoginData) = LoginDomain(
        id = 1,
        name = login.name,
        agency = login.agency,
        account = login.account,
        balance = login.balance
    )
}
