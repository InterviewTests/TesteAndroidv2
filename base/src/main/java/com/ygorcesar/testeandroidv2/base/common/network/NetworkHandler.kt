package com.ygorcesar.testeandroidv2.base.common.network

import android.content.Context
import com.ygorcesar.testeandroidv2.base.extensions.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which handles device network connection.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected ?: false
}