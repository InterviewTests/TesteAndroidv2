package com.nandoligeiro.safrando.data.login.repository

import com.nandoligeiro.safrando.data.login.datasource.LocalLoginDataSource
import com.nandoligeiro.safrando.domain.login.repository.LocalLoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalLoginRepositoryImpl @Inject constructor(
    private val localLoginDataSource: LocalLoginDataSource,
) : LocalLoginRepository {
    override suspend fun save(user: String) = localLoginDataSource.saveLogin(user)

    override suspend fun getUser() = localLoginDataSource.getLocalLogin()
}
