package com.farage.testeandroidv2.util

import android.os.Build
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.toMoney(): String {
    val num = this.replace(".", "")
    val numFormat = NumberFormat.getCurrencyInstance().format(num.toDouble())
    return numFormat
}

fun String.dateFormat(): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var date = LocalDate.parse(this)
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return date.format(dateFormat)
    }
    return this
}

fun String.isValidPassword(): Boolean {

    val pattern: Pattern
    val matcher: Matcher

    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@*#$%^&+=])(?=\\S+$).{4,}$"

    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern.matcher(this)

    return matcher.matches()
}

fun String.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isCPFValid() : Boolean{
    val cpfClean = this.replace(".", "").replace("-", "")

    //## check if size is eleven
    if (cpfClean.length != 11)
        return false

    //## check if is number
    try {
        val number  = cpfClean.toLong()
    }catch (e : Exception){
        return false
    }

    //continue
    var dvCurrent10 = cpfClean.substring(9,10).toInt()
    var dvCurrent11= cpfClean.substring(10,11).toInt()

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