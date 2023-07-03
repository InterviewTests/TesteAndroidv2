package com.nandoligeiro.safrando.domain.localUser.usecase.getLocalUser

import kotlinx.coroutines.flow.Flow

interface GetLocalUserUseCase {
    suspend operator fun invoke(): Flow<String>
}
