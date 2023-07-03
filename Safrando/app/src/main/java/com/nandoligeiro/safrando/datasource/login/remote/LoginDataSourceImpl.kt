package com.nandoligeiro.safrando.datasource.login.remote

import com.nandoligeiro.safrando.data.api.SafrandoService
import com.nandoligeiro.safrando.data.login.datasource.RemoteLoginDataSource
import com.nandoligeiro.safrando.datasource.login.entity.LoginEntity
import com.nandoligeiro.safrando.datasource.login.mapper.LoginEntityToDataMapper
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val api: SafrandoService,
    private val toDataMapper: LoginEntityToDataMapper
) : RemoteLoginDataSource {
    override suspend fun postLogin(
        user: String, password: String
    ) = toDataMapper.toDataLogin(mockApiLogin()) //(api.postLogin(user, password))
    private fun mockApiLogin() = LoginEntity(
        id = 1,
        name = "Jose da Silva Teste",
        agency = "2050",
        account = "01.11122-4",
        balance = "10000"
    )
}
