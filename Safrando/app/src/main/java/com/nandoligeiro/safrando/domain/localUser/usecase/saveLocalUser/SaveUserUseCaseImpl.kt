package com.nandoligeiro.safrando.domain.localUser.usecase.saveLocalUser

import com.nandoligeiro.safrando.domain.login.repository.LocalLoginRepository
import javax.inject.Inject

class SaveUserUseCaseImpl @Inject constructor(
    private val localLoginRepository: LocalLoginRepository
): SaveUserUseCase {

    override suspend fun invoke(user: String) {
        localLoginRepository.save(user)
    }
}
