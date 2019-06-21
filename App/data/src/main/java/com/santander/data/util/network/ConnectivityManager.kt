package com.santander.data.util.network

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityManager(private val context: Context) {

    fun isConnected() : Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connMgr.activeNetworkInfo?.isConnected == true
    }
}