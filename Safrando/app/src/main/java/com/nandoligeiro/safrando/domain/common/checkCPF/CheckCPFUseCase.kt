package com.nandoligeiro.safrando.domain.common.checkCPF

interface CheckCPFUseCase {
    operator fun invoke(value: String): Boolean
}