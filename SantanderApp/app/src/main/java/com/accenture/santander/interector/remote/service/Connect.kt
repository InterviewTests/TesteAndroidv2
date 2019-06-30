package com.accenture.santander.interector.remote.service

import android.content.Context
import android.net.ConnectivityManager

class Connect(val context: Context) : IConnect {
    override fun verifyConnection(): Boolean =
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.getActiveNetworkInfo() != null
        } catch (e: Exception) {
            false
        }
}