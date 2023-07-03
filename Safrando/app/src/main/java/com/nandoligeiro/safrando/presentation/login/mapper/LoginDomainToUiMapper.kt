package com.nandoligeiro.safrando.presentation.login.mapper

import com.nandoligeiro.safrando.domain.login.model.LoginDomain
import com.nandoligeiro.safrando.presentation.login.model.UiLoginModel

class LoginDomainToUiMapper {
    fun toUiLogin(login: LoginDomain) = UiLoginModel(
        id = 1,
        name = login.name,
        agency = login.agency,
        account = login.account,
        balance = login.balance
    )
}
