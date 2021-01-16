package com.jeanjnap.data.util.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal
import java.text.ParseException

class BigDecimalAdapter {

    @FromJson
    @Throws(ParseException::class)
    fun fromJson(string: String) = BigDecimal(string)

    @ToJson
    @Throws(ParseException::class)
    fun toJson(value: BigDecimal) = value.toString()
}
