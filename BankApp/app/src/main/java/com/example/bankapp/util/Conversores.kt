package com.example.bankapp.util

import java.text.SimpleDateFormat

class Conversores {
    fun converterDataddMMyyyy(data: String): String {

        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd/MM/yyyy")

        return formatter.format(parser.parse(data))
    }
}