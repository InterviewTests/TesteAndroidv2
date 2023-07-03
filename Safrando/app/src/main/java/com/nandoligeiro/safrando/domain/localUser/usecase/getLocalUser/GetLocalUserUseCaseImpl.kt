package com.nandoligeiro.safrando.domain.localUser.usecase.getLocalUser

import com.nandoligeiro.safrando.domain.login.repository.LocalLoginRepository
import javax.inject.Inject

class GetLocalUserUseCaseImpl @Inject constructor(
    private val localLoginRepository: LocalLoginRepository
) : GetLocalUserUseCase {
    override suspend fun invoke() = localLoginRepository.getUser()
}
