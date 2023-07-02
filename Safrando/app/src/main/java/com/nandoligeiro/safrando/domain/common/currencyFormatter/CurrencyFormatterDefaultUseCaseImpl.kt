package com.nandoligeiro.safrando.domain.common.currencyFormatter

import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class CurrencyFormatterDefaultUseCaseImpl @Inject constructor() : CurrencyFormatterUseCase {
    override fun invoke(amount: String): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        return numberFormat.format(amount.toDouble())
    }
}
