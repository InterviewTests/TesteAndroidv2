package br.com.flaviokreis.santanderv2.utils

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS

object Validators {
    private val CHARACTER_SPECIAL = "[~_}{\\[\\]\$&+,:;=?@#|/'<>.^*()%!-]".toRegex()

    private val CHARACTER_CAPITALIZED = "[A-Z]".toRegex()

    private val CHARACTERS = "[a-zA-Z]".toRegex()

    private val NUMBER = "[0-9]".toRegex()

    fun isUsernameValid(username: String): Boolean {
        return username.isNotEmpty() && (validEmail(username) || validCpf(username))
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty() && NUMBER.containsMatchIn(password)
            && CHARACTER_SPECIAL.containsMatchIn(password)
            && CHARACTER_CAPITALIZED.containsMatchIn(password)
            && CHARACTERS.containsMatchIn(password)
    }

    private fun validEmail(email: String) = EMAIL_ADDRESS.matcher(email).matches()

    private fun validCpf(cpf: String): Boolean {
        if (cpf.length != 11) return false
        if (cpf.toIntOrNull() == null) return false

        val dvCurrent10 = cpf.substring(9, 10).toInt()
        val dvCurrent11 = cpf.substring(10, 11).toInt()

        val cpfNineFirst = IntArray(9)
        var i = 9
        while (i > 0) {
            cpfNineFirst[i - 1] = cpf.substring(i - 1, i).toInt()
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