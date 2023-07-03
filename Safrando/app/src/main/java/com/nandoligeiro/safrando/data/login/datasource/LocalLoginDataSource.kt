package com.nandoligeiro.safrando.data.login.datasource

import kotlinx.coroutines.flow.Flow

interface LocalLoginDataSource {
    suspend fun saveLogin(user: String)
    suspend fun getLocalLogin(): Flow<String>
}
