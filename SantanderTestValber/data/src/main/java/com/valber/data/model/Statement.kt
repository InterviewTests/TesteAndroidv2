package com.valber.data.model

import com.valber.data.extensions.empty
import java.math.RoundingMode
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

data class Statement(val title: String,
                     val desc: String,
                     val date: String,
                     val value: Double
) {
    companion object {
        fun toEmpty() = Statement(String.empty(), String.empty(), String.empty(), 0.0)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Statement

        if (title != other.title) return false
        if (desc != other.desc) return false
        if (date != other.date) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

}

fun Statement.showDate() = SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))

fun Statement.showValue() = NumberFormat.getCurrencyInstance().format(value.toBigDecimal().setScale(1, RoundingMode.CEILING))