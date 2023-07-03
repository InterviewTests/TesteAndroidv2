package com.nandoligeiro.safrando.datasource.login.mapper

import com.nandoligeiro.safrando.data.login.model.LoginData
import com.nandoligeiro.safrando.datasource.login.entity.LoginEntity

class LoginEntityToDataMapper {
    fun toDataLogin(login: LoginEntity) = LoginData(
        id = 1,
        name = login.name,
        agency = login.agency,
        account = login.account,
        balance = login.balance
    )
}
