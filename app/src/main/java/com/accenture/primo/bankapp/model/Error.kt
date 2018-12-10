package com.accenture.primo.bankapp.model

import android.util.Log

data class Error(val code:Int, val menssage:String) {

    fun toLog():String {
        val strMessage:String = "[$code] $menssage"
        Log.e("Server Error","$strMessage")
        return strMessage
    }
}