package fingerfire.com.extractbank.utils

import android.content.Context
import android.widget.Toast
import java.text.NumberFormat
import java.util.Locale

object Utils {

    fun formatToCurrency(value: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return format.format(value)
    }

    fun formatAccountNumber(agencyNumber: String, accountNumber: String): String {
        val length = accountNumber.length
        return String.format(
            "%s / %s.%s-%s",
            agencyNumber,
            accountNumber.substring(0, 2),
            accountNumber.substring(2, length - 2),
            accountNumber.substring(length - 1, length)
        )
    }

    fun showError(errorMessage: String, context: Context) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}