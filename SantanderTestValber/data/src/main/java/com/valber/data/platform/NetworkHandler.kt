package com.valber.data.platform

import android.content.Context
import com.valber.data.extensions.networkInfo


class NetworkHandler
constructor(private val context: Context) {
    val isConnected get() =context.networkInfo?.isConnected
}