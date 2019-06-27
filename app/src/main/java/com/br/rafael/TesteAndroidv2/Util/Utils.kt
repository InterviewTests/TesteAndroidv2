package com.br.rafael.TesteAndroidv2.Util


import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {


    companion object {

        fun converMoney(value: Float): String {
            val ptBr = Locale("pt", "BR")
            return NumberFormat.getCurrencyInstance(ptBr).format(value)
        }

        fun stringData(inputDateStr: String): String {

            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = inputFormat.parse(inputDateStr)
            return outputFormat.format(date)
        }


        fun formatAgencyAccount(agency: String, bank: String): String {


            var bankFormat = bank.substring(0, 2) + "" + bank.substring(2, bank.length - 1) + "-" + bank.substring(bank.length - 1)


            return "$agency / $bankFormat"


        }
    }
}