package br.com.rms.bankapp.utils
import java.text.NumberFormat
import java.util.*

class UtilsMoneyFormatting {

    companion object {

        fun simpleMoneyFormmat(value: Double): String {
            val ptBr = Locale("pt", "BR")
            val formattedValue: String
            val numberFormat = NumberFormat.getCurrencyInstance(ptBr)
            formattedValue = numberFormat.format(value)
            return formattedValue
        }
    }
}