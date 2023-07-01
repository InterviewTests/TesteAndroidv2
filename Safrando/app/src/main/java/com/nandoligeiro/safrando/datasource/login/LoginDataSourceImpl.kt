package com.nandoligeiro.safrando.datasource.login

import com.nandoligeiro.safrando.data.api.SafrandoService
import com.nandoligeiro.safrando.data.login.datasource.LoginDataSource
import com.nandoligeiro.safrando.datasource.login.mapper.LoginEntityToDataMapper
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val api: SafrandoService,
    private val toDataMapper: LoginEntityToDataMapper
) : LoginDataSource {
    override suspend fun postLogin(
        user: String, password: String
    ) = toDataMapper.toDataLogin(api.postLogin(user, password))
}