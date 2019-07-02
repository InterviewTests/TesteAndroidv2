package io.github.maikotrindade.bankapp.base.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object StringsUtil {

    fun convertDoubleToCurrency(double: Double): String {
        val ptBr = Locale("pt", "BR")
        return NumberFormat.getCurrencyInstance(ptBr).format(double)
    }

    fun formatDate(unformattedDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val date = inputFormat.parse(unformattedDate)
        return outputFormat.format(date)
    }

}