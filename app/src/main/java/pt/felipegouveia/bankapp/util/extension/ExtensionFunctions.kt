package pt.felipegouveia.bankapp.util.extension

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Checks if the user is valid as per the following user policy.
 * User can be a valid email.
 * User can be a valid CPF.
 *
 * @return - true if the user is valid as per the user policy.
 */
fun String.isValidUser(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches() || this.isValidCPF()
}

/**
 * Checks if the password is valid as per the following password policy.
 * Password should contain at least one alphanumeric character.
 * Password should contain at least one capital letter.
 * Password should contain at least one special character.
 * Allowed special characters: "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
 *
 * @return - true if the password is valid as per the password policy.
 */
fun String.isValidPassword(): Boolean {
    var valid = true

    // Password should contain at least one capital letter
    var exp = ".*[A-Z].*"
    var pattern = Pattern.compile(exp)
    var matcher = pattern.matcher(this)
    if (!matcher.matches()) {
        valid = false
    }

    // Password should contain at least one special character
    // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
    exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(this)
    if (!matcher.matches()) {
        valid = false
    }

    // Password should contain at least one alphanumeric
    exp = ".*[a-z0-9].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(this)
    if (!matcher.matches()) {
        valid = false
    }
    return valid
}

fun String.isValidCPF(): Boolean{
    val cpfClean = this.replace(".", "").replace("-", "")

    //## check if size is eleven
    if (cpfClean.length != 11)
        return false

    //## check if is number
    try {
        val cpfClean  = cpfClean.toLong()
    }catch (e : Exception){
        return false
    }

    //continue
    var dvCurrent10 = cpfClean.substring(9,10).toInt()
    var dvCurrent11 = cpfClean.substring(10,11).toInt()

    //the sum of the nine first digits determines the tenth digit
    val cpfNineFirst = IntArray(9)
    var i = 9
    while (i > 0 ) {
        cpfNineFirst[i-1] = cpfClean.substring(i-1, i).toInt()
        i--
    }
    //multiple the nine digits for your weights: 10,9..2
    var sumProductNine = IntArray(9)
    var weight = 10
    var position = 0
    while (weight >= 2){
        sumProductNine[position] = weight * cpfNineFirst[position]
        weight--
        position++
    }
    //Verify the nineth digit
    var dvForTenthDigit = sumProductNine.sum() % 11
    dvForTenthDigit = 11 - dvForTenthDigit //rule for tenth digit
    if(dvForTenthDigit > 9)
        dvForTenthDigit = 0
    if (dvForTenthDigit != dvCurrent10)
        return false

    //### verify tenth digit
    var cpfTenFirst = cpfNineFirst.copyOf(10)
    cpfTenFirst[9] = dvCurrent10
    //multiple the nine digits for your weights: 10,9..2
    var sumProductTen = IntArray(10)
    var w = 11
    var p = 0
    while (w >= 2){
        sumProductTen[p] = w * cpfTenFirst[p]
        w--
        p++
    }
    //Verify the nineth digit
    var dvForeleventhDigit = sumProductTen.sum() % 11
    dvForeleventhDigit = 11 - dvForeleventhDigit //rule for tenth digit
    if(dvForeleventhDigit > 9)
        dvForeleventhDigit = 0
    if (dvForeleventhDigit != dvCurrent11)
        return false

    return true
}

/**
 * Displays a message to the user using Toast
 *
 */
fun Context.toast(resourceId: Int) =
    Toast.makeText(this, this.getString(resourceId), Toast.LENGTH_SHORT).show()

/**
 * Converts a double into Real currency
 *
 */
fun Double.toReal(): String {
    val local = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance()
        .currency
        ?.getSymbol(local)
        ?.format(
            this.toBigDecimal()
                .setScale(1, RoundingMode.CEILING
                )
        )?: ""
}


