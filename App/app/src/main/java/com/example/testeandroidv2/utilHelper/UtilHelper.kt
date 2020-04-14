package com.example.testeandroidv2.utilHelper

import android.util.Patterns
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.regex.Pattern

class UtilHelper {

    fun currency(value: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        return (format.format(value))
    }

    fun formatAccount(agency: String): String {

        if (agency.length < 9)
            return "error"

        var result = String()
        for ((i, m) in agency.toCharArray().withIndex()) {
            when(i) {
                2 -> result += ".$m"
                8 -> result += "-$m"
                else -> result += m
            }
        }
        return result
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
        return if (value.contains("@"))
        {
            val emailRegex = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+")
            emailRegex.matcher(value).matches()
        }
        else
            validateCPF(value)
    }

    fun dateFormat(sDate: String): String {

        return try {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val x = formatter.parse(sDate)
            SimpleDateFormat("dd/MM/yyyy").format(x)
        }
        catch (e: Exception){
            "error"
        }

    }

    fun validateCPF(cpf : String) : Boolean {

        val cpfClean = cpf.replace(".", "").replace("-", "")

        if (cpfClean == "00000000000" || cpfClean == "11111111111" ||
            cpfClean == "22222222222" || cpfClean == "33333333333" ||
            cpfClean == "44444444444" || cpfClean == "55555555555" ||
            cpfClean == "66666666666" || cpfClean == "77777777777" ||
            cpfClean == "88888888888" || cpfClean == "99999999999")
            return(false)

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

}


