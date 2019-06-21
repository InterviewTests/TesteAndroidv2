package com.santander.app.core.util.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DEFAULT_API_DATE_PATTERN = "yyyy-MM-dd"
const val DEFAULT_EXHIBITION_DATE_PATTERN = "dd/MM/yyyy"

fun String?.toDate(parsePattern: String = DEFAULT_API_DATE_PATTERN): Date? {
    return try {
        SimpleDateFormat(parsePattern, Locale.getDefault()).parse(this).takeIf { !isNullOrEmpty() }
    } catch (e: ParseException) {
        null
    }
}

fun Date.formatted(showPattern: String = DEFAULT_EXHIBITION_DATE_PATTERN): String? {
    return SimpleDateFormat(showPattern, Locale.getDefault()).format(this)
}
