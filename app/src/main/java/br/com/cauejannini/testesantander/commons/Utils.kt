package br.com.cauejannini.testesantander.commons

import android.content.Context
import android.widget.Toast
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

object Utils {

    fun showToast(context: Context?, text: String?) {

        if (context != null && text != null) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun valorMonetarioParaDouble(valor: String?): Double? {
        if (valor != null) {

            val df = DecimalFormat("#,##0.00")
            try {
                val number = df.parse(valor)
                number?.let{
                    return it.toDouble()
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun doubleParaValorMonetario(valor: Double?): String {
        valor?.let{
            val df = DecimalFormat("#,##0.00")
            return df.format(it)
        }
        return "-"
    }

    fun dataApiParaApp(data: String?): String {
        data?.let{
            val array = data.split("-")
            if (array.size == 3) {
                return String.format("%s/%s/%s", array.get(2), array.get(1),array.get(0))
            }
        }
        return "-"
    }

    fun doisDecimais(valor: Double?): String? {
        if (valor != null) {
            val decimalFormat = DecimalFormat(
                "#,##0.00",
                DecimalFormatSymbols(Locale("pt", "BR"))
            )
            return decimalFormat.format(valor)
        }
        return "-"
    }

    fun currencyBrazil(valor: Double?): String {
        val doisDecimais = doisDecimais(valor)

        return "R$ $doisDecimais"
    }

}