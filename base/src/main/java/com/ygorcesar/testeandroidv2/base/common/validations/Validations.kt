package com.ygorcesar.testeandroidv2.base.common.validations

import android.support.v4.util.PatternsCompat
import com.ygorcesar.testeandroidv2.base.common.utils.RegexUtils

object Validations {

    fun hasSpecialCharacter(string: String) = RegexUtils.CHARACTER_SPECIAL.find(string) != null

    fun hasCapitalizedLetter(string: String) = RegexUtils.CHARACTER_CAPITALIZED.find(string) != null

    fun hasNumber(string: String) = RegexUtils.NUMBER.find(string) != null

    fun isEmailValid(email: String) = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    fun isCpfValid(cpf: String): Boolean {
        val cpfClean = cpf.replace(".", "").replace("-", "")

        if (cpfClean.length != 11)
            return false

        val dvCurrent10 = cpfClean.substring(9, 10).toInt()
        val dvCurrent11 = cpfClean.substring(10, 11).toInt()

        val cpfNineFirst = IntArray(9)
        var i = 9
        while (i > 0) {
            cpfNineFirst[i - 1] = cpfClean.substring(i - 1, i).toInt()
            i--
        }
        val sumProductNine = IntArray(9)
        var weight = 10
        var position = 0
        while (weight >= 2) {
            sumProductNine[position] = weight * cpfNineFirst[position]
            weight--
            position++
        }
        var dvForTenthDigit = sumProductNine.sum() % 11
        dvForTenthDigit = 11 - dvForTenthDigit
        if (dvForTenthDigit > 9)
            dvForTenthDigit = 0
        if (dvForTenthDigit != dvCurrent10)
            return false


        val cpfTenFirst = cpfNineFirst.copyOf(10)
        cpfTenFirst[9] = dvCurrent10
        val sumProductTen = IntArray(10)
        var w = 11
        var p = 0
        while (w >= 2) {
            sumProductTen[p] = w * cpfTenFirst[p]
            w--
            p++
        }
        var dvForeleventhDigit = sumProductTen.sum() % 11
        dvForeleventhDigit = 11 - dvForeleventhDigit
        if (dvForeleventhDigit > 9)
            dvForeleventhDigit = 0
        if (dvForeleventhDigit != dvCurrent11)
            return false

        return true
    }

}
