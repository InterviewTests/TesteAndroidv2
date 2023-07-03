package com.nandoligeiro.safrando.domain.common.checkEmail

interface CheckEmailUseCase {
    operator fun invoke(value: String): Boolean
}