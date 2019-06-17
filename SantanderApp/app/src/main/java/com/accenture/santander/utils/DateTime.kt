package com.accenture.santander.utils

import java.text.SimpleDateFormat
import java.util.*


object DateTime {

    @JvmStatic
    fun mask(date: String?): String {
        return if (date != null) SimpleDateFormat("dd/MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))
            ?: "" else ""
    }

    @JvmStatic
    fun conversor(date: String): Date {
        return SimpleDateFormat("yyyy-MM-dd").parse(date)
    }
}