package com.example.ibm_test.utils

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import javax.inject.Inject


class Connectivity @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ConnectivityContract {

    override fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo

        var isAvailable = false

        if (networkInfo != null && networkInfo.isConnected) {
            isAvailable = true
        }
        return isAvailable
    }

    override fun listenToLoseNetwork(onData: (isNetworkLose: Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    onData(true)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    onData(false)
                }
            })
        }
    }
}

interface ConnectivityContract {
    fun isNetworkAvailable(): Boolean
    fun listenToLoseNetwork(onData: (isNetworkLose: Boolean) -> Unit)
}