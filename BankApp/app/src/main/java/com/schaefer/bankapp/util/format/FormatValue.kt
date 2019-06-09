import android.annotation.SuppressLint
import java.text.NumberFormat
import java.util.*

object FormatValue {
    @JvmStatic
    fun convertIntToFloat(value: Int): Float {
        return value / 100.toFloat()
    }

    @JvmStatic
    fun convertStringToFloat(str: String): Float {
        val value: String
        var retorno = 0f
        try {
            value = str.replace("[R$]".toRegex(), "").replace("[,]+".toRegex(), "")
                .replace("[.]+".toRegex(), "")
            retorno = java.lang.Float.parseFloat(value) / 100
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        return retorno
    }

    @JvmStatic
    fun convertStringToInt(str: String): Int {
        val value: String
        var retorno = 0
        try {
            value = str.replace("[R$]".toRegex(), "").replace("[,]+".toRegex(), "")
                .replace("[.]+".toRegex(), "").replace("[\\s+]".toRegex(), "")

            // Transformamos o número que está escrito no EditText em Integer.
            retorno = Integer.parseInt(value)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        return retorno
    }

    @JvmStatic
    fun formatIntToString(va: Int): String {
        val value = convertIntToFloat(va)
        val str = getFormatLocale().format(value).replace("[R$]".toRegex(), "")

        return "R$ $str"
    }

    @JvmStatic
    fun formatFloatToString(va: Float): String {
        val str = getFormatLocale().format(va).replace("[R$]".toRegex(), "")
        return "R$ $str"
    }


    @JvmStatic
    fun formatIntToStringPercentage(va: Int): String {
        val value = convertIntToFloat(va)

        @SuppressLint("DefaultLocale") var format = String.format("%.2f", value)
        format = format.replace("[.]".toRegex(), ",") + "%"
        return format
    }

    @JvmStatic
    fun getFormatLocale(): NumberFormat {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    }

}