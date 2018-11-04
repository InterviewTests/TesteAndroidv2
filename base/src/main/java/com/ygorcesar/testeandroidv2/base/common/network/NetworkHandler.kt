package com.ygorcesar.testeandroidv2.base.common.network

import android.content.Context
import br.com.desafioandroidenjoei.testing.OpenForTesting
import com.ygorcesar.testeandroidv2.base.extensions.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which handles device network connection.
 */
@OpenForTesting
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected ?: false
}