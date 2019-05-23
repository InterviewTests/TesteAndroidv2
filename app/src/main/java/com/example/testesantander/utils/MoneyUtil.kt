package com.example.testesantander.utils

import android.content.Context
import com.example.testesantander.R
import java.text.NumberFormat
import java.util.*

class MoneyUtil {
    companion object {
        fun moneyPtBr(value: Double): String{
            val df = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            return df.format(value)
        }
    }
}