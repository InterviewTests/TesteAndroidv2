package com.nandoligeiro.safrando.domain.login.repository

import kotlinx.coroutines.flow.Flow

interface LocalLoginRepository {
    suspend fun save(user: String)
    suspend fun getUser(): Flow<String>
}
