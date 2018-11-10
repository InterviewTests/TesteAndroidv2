package br.com.ibm.teste.android.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 17:46
 */
class Utils {

    companion object {

        /**
         * Check if there is internet on the phone
         */
        fun isConnected(mContext: Context): Boolean {
            val connectivityManager =
                    mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo?.isConnected ?: false
        }
    }
}