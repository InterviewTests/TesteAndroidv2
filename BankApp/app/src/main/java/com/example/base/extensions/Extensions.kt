package com.example.base.extensions

import android.app.Activity
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode
import java.text.NumberFormat

inline fun <reified T : Parcelable> Activity.requireParcelable(key: String): T {
    return intent.getParcelableExtra(key)!!
}

fun AppCompatActivity.hideToolbar() {
    supportActionBar!!.hide()
}

fun Double.toMoneyFormat(): String {
    return NumberFormat.getCurrencyInstance()
        .format(this.toBigDecimal().setScale(1, RoundingMode.CEILING))
}

fun String.toBrazilCalendar(): String {
    val year = this.substring(0, 4)
    val month = this.substring(5, 7)
    val day = this.substring(8, 10)
    return "$day/$month/$year"
}
