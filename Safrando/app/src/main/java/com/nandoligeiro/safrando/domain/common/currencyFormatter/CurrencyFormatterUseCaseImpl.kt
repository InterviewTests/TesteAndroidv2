package com.nandoligeiro.safrando.domain.common.currencyFormatter

import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class CurrencyFormatterUseCaseImpl @Inject constructor(): CurrencyFormatterUseCase {
    override fun invoke(amount: String): String {
        val locale = Locale("pt", "BR")
        val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format(amount.toDouble())
    }
}
