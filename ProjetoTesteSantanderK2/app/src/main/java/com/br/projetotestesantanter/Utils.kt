package com.br.projetotestesantanter

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        fun  converMoney( value : Float) : String {
            val ptBr = Locale("pt", "BR")
            val valueString = NumberFormat.getCurrencyInstance(ptBr).format(value)
            return valueString;

        }

        fun stringData(inputDateStr : String) : String {

            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("dd MMM yyyy")
            val date = inputFormat.parse(inputDateStr)
            return outputFormat.format(date)
        }
    }

}