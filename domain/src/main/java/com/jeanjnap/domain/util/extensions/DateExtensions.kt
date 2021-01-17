package com.jeanjnap.domain.util.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

private const val DATE_FORMAT = "dd/MM/yyyy"

@SuppressLint("SimpleDateFormat")
fun Date.formatToString(format: String = DATE_FORMAT): String? {
    return SimpleDateFormat(format).format(this)
}
