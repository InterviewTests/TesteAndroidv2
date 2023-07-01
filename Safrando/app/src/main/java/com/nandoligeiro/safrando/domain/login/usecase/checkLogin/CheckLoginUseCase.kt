package com.nandoligeiro.safrando.domain.login.usecase.checkLogin

interface CheckLoginUseCase {
    operator fun invoke(user: String, password: String): Boolean
}