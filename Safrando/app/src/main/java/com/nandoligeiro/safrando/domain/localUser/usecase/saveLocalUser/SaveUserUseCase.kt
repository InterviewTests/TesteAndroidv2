package com.nandoligeiro.safrando.domain.localUser.usecase.saveLocalUser

interface SaveUserUseCase {
    suspend operator fun invoke(data: String)
}
