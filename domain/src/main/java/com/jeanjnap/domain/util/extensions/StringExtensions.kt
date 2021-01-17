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
