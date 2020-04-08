package com.example.testeandroidv2.util

import android.util.Patterns
import java.text.NumberFormat
import java.text.SimpleDateFormat


fun currency(value: Double): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    return (format.format(value))
}

fun formatAccount(agency: String): String {
    var teste = String()
    for ((i, m) in agency.toCharArray().withIndex()) {
        when(i) {
            2 -> teste += ".$m"
            8 -> teste += "-$m"
            else -> teste += m
        }
    }
    return teste
}

fun checkPassword(passwordToTest: String) : Boolean {

    val uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val numbers = "0123456789"
    val special = "[\$&+,:;=\\?@#|/'<>.^*()%!-_~{}]"

    var result = false

    if(
        passwordToTest.matches(Regex(".*[$uppercaseLetters].*")) &&
        passwordToTest.matches( Regex(".*[$numbers].*")) &&
        passwordToTest.matches( Regex(".*[$special].*"))) {
        result = true
    }
    return result
}

fun checkUser(value: String): Boolean {
    if (value.contains("@"))
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    else
        return validateCPF(value)
}

fun dateFormat(sDate: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val x = formatter.parse(sDate)

    return SimpleDateFormat("dd/MM/yyyy").format(x)
}


fun validateCPF(cpf : String) : Boolean{

    val cpfClean = cpf.replace(".", "").replace("-", "")

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

