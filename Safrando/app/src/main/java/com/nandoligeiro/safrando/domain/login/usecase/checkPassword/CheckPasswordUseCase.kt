package com.nandoligeiro.safrando.domain.login.usecase.checkPassword

interface CheckPasswordUseCase {
    operator fun invoke(password: String): Boolean
}