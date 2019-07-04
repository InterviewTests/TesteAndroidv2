package com.accenture.santander.utils

import com.accenture.santander.components.notNull
import java.text.SimpleDateFormat
import java.util.*


object DateTime {

    @JvmStatic
    fun mask(date: String?): String =
        if (date.notNull())
            SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
                .format(
                    SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR")).parse(date)
                        ?: Date()
                )
                ?: ""
        else
            ""


    @JvmStatic
    fun conversor(date: String): Date =
        SimpleDateFormat("yyyy-MM-dd").parse(date)

}