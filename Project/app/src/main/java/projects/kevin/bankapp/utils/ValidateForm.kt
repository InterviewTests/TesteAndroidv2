package projects.kevin.bankapp.utils

import android.app.Activity
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode


fun validatePassword(pass: String, act: Activity): Boolean {
    val regexAlpha = "[a-zA-z0-9]".toRegex()
    val regexSpecial = "[!@#\$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]".toRegex()
    val regexUpper = "[A-Z]".toRegex()
        if(regexAlpha.containsMatchIn(pass)) {
            if(regexUpper.containsMatchIn(pass)) {
                if(regexSpecial.containsMatchIn(pass)) {
                    return true
                }
            }
        }

    return false
}

fun formatMoney(balance: BigDecimal): String {
//        val numberFmt = NumberFormat.getCurrencyInstance()
//        val numberFmt = java.text.DecimalFormat("#,###,##0,00")
    return balance.setScale(2, RoundingMode.HALF_EVEN).toString()
}
