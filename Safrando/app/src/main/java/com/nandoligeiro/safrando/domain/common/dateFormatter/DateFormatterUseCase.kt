package com.nandoligeiro.safrando.domain.common.dateFormatter

interface DateFormatterUseCase {
    operator fun invoke(date: String): String
}