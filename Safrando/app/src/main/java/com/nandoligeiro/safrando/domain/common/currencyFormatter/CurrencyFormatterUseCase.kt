package com.nandoligeiro.safrando.domain.common.currencyFormatter

interface CurrencyFormatterUseCase {
   operator fun invoke(amount: String): String
}