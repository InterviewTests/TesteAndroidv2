package com.accenture.santander.utils

import android.content.Context
import android.net.ConnectivityManager

object Connect {
    fun verifyConnection(context: Context): Boolean {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.getActiveNetworkInfo() != null
        } catch (e: Exception) {
            return false
        }

    }
}