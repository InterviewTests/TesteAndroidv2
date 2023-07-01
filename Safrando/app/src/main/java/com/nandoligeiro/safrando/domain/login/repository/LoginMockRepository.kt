package com.nandoligeiro.safrando.domain.login.repository

import com.nandoligeiro.safrando.domain.login.model.LoginDomain
import javax.inject.Inject

class LoginMockRepository @Inject constructor() : LoginRepository {
    override suspend fun postLogin(user: String, password: String): LoginDomain = LoginDomain(
        id = 1,
        name = "Jose da Silva Teste",
        agency = "2050",
        account = "01.11122-4",
        balance = "10000"
    )
}