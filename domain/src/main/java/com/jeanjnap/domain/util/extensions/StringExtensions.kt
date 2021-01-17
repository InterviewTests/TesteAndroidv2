package com.jeanjnap.domain.util.extensions

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
private const val AGENCY_MASK = "##.######-#"
private const val NOT_REPLACEABLE_CHAR = '#'
private const val EMPTY_TEXT = ""
private const val ZERO_VALUE = 0
private const val ONE_VALUE = 1
private const val EIGHT_VALUE = 8
private const val NINE_VALUE = 9
private const val TEN_VALUE = 10
private const val ELEVEN_VALUE = 11

@SuppressLint("SimpleDateFormat")
fun String.formatStringAsDate(format: String = DEFAULT_DATE_FORMAT): Date? {
    return try {
        SimpleDateFormat(format).parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun String.formatAgency(): String {
    var res = EMPTY_TEXT
    var interator = ZERO_VALUE
    AGENCY_MASK.forEachIndexed { _, currentChar ->
        if (currentChar == NOT_REPLACEABLE_CHAR) {
            try {
                res += this[interator]
                interator++
            } catch (e: StringIndexOutOfBoundsException) {
                return res
            }
        } else {
            res += currentChar
        }
    }
    return res
}

fun String.isCPF(): Boolean {
    if (this.isEmpty()) return false

    val numbers = arrayListOf<Int>()

    this.filter { it.isDigit() }.forEach {
        numbers.add(it.toString().toInt())
    }

    if (numbers.size != ELEVEN_VALUE) return false

    (ZERO_VALUE..NINE_VALUE).forEach { n ->
        val digits = arrayListOf<Int>()
        (ZERO_VALUE..TEN_VALUE).forEach { _ -> digits.add(n) }
        if (numbers == digits) return false
    }

    val dv1 =
        ((ZERO_VALUE..EIGHT_VALUE).sumBy { (it + ONE_VALUE) * numbers[it] }).rem(ELEVEN_VALUE).let {
            if (it >= TEN_VALUE) ZERO_VALUE else it
        }

    val dv2 = ((ZERO_VALUE..EIGHT_VALUE).sumBy { it * numbers[it] }
        .let { (it + (dv1 * NINE_VALUE)).rem(ELEVEN_VALUE) }).let {
        if (it >= TEN_VALUE) ZERO_VALUE else it
    }

    return numbers[NINE_VALUE] == dv1 && numbers[TEN_VALUE] == dv2
}
