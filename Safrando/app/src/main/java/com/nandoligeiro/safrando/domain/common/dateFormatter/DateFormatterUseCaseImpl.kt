package com.nandoligeiro.safrando.domain.common.dateFormatter

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateFormatterUseCaseImpl @Inject constructor() : DateFormatterUseCase {

    companion object {
        const val DATE_PATTERN = "dd/MM/yyyy"
        const val LANG = "pt"
        const val COUNTRY = "BR"
    }

    override fun invoke(date: String): String {

        val locale = Locale(LANG, COUNTRY)
        val sdf = SimpleDateFormat(DATE_PATTERN, locale)

        return sdf.format(date)
    }
}
