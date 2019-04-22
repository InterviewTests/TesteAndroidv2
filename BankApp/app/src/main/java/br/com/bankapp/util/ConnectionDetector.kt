package br.com.bankapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionDetector {

companion object{
    fun isConnectionDetector(context: Context): Boolean{
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null){
            val info = connectivity.allNetworkInfo
            if (info != null){
                for (i in info.indices){
                    if (info[i].state === NetworkInfo.State.CONNECTED){
                        return true
                    }
                }
            }
        }
        return false
    }
}

}