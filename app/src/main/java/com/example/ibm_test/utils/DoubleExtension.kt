package com.example.ibm_test.utils

import android.content.Context
import java.text.NumberFormat

fun Double.toMoney(context: Context): String {
    val convert = NumberFormat.getNumberInstance(context.resources.configuration.locale).format(this)
    return "R$${convert}"
}