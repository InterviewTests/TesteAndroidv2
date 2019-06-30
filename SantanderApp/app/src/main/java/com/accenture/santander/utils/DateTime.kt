package com.accenture.santander.utils

import com.accenture.santander.components.notNull
import java.text.SimpleDateFormat
import java.util.*


object DateTime {

    @JvmStatic
    fun mask(date: String?): String =
        if (date.notNull()) SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))
            ?: "" else ""

    @JvmStatic
    fun conversor(date: String): Date =
        SimpleDateFormat("yyyy-MM-dd").parse(date)

}