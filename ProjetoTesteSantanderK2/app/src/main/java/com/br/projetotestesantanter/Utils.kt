package com.br.projetotestesantanter

import android.content.Context
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.ConnectivityManager



class Utils {

    companion object {

        fun  converMoney(value : Float) : String {
            val ptBr = Locale("pt", "BR")
            return NumberFormat.getCurrencyInstance(ptBr).format(value)
        }

        fun stringData(inputDateStr : String) : String {

            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = inputFormat.parse(inputDateStr)
            return outputFormat.format(date)
        }

        fun isConected(context: Context): Boolean {
            val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

            if (cm != null) {
                val ni = cm.activeNetworkInfo

                return ni != null && ni.isConnected
            }

            return false
        }

        fun formatAgencyAccount(agency : String, bank : String) : String{


            var bankFormat = bank.substring(0,2) + "." + bank.substring(2 ,bank.length -1) + "-" + bank.substring(bank.length-1)

            return "$agency / $bankFormat"


        }
    }

}