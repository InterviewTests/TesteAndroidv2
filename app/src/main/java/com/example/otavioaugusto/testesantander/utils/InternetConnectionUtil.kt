package com.example.otavioaugusto.testesantander.utils

import android.content.Context
import android.net.ConnectivityManager

open class InternetConnectionUtil {

    companion object {
        fun isAnyInternetConnected(context: Context): Boolean {
            val connectivityManager: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo

            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

}