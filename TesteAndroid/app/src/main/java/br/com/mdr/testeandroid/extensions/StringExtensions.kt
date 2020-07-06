package br.com.mdr.testeandroid.extensions

import android.text.TextUtils
import br.com.mdr.testeandroid.util.Constants.Companion.REGEX_SPECIAL_CHARS

fun String.hasDigit(): Boolean {
    var hasDigit = false
    for (letter in this) {
        if (!hasDigit)
            hasDigit = letter.isDigit()
    }
    return hasDigit
}

fun String.hasLetter(): Boolean {
    var hasLetter = false
    for (letter in this) {
        if (!hasLetter)
            hasLetter = !letter.isDigit()
    }
    return hasLetter
}

fun String.isEmail(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.hasSpecialCharacters(): Boolean {
    val regex = REGEX_SPECIAL_CHARS.toRegex()
    return regex.containsMatchIn(this)
}

fun String.isCPF(): Boolean {
    if (TextUtils.isEmpty(this)) return false

    val numbers = arrayListOf<Int>()

    this.filter { it.isDigit() }.forEach {
        numbers.add(it.toString().toInt())
    }

    if (numbers.size != 11) return false

    //repeticao
    (0..9).forEach { n ->
        val digits = arrayListOf<Int>()
        (0..10).forEach { digits.add(n) }
        if (numbers == digits) return false
    }

    //digito 1
    val dv1 = ((0..8).sumBy { (it + 1) * numbers[it] }).rem(11).let {
        if (it >= 10) 0 else it
    }

    val dv2 = ((0..8).sumBy { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
        if (it >= 10) 0 else it
    }

    return numbers[9] == dv1 && numbers[10] == dv2
}