package com.nandoligeiro.safrando.domain.login.usecase

import com.nandoligeiro.safrando.domain.login.model.LoginDomain
import com.nandoligeiro.safrando.domain.login.result.LoginResult

interface PostLoginUseCase {
    suspend operator fun invoke(user: String, password: String ): LoginResult<LoginDomain>
}
