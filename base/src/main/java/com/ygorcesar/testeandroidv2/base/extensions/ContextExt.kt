package com.ygorcesar.testeandroidv2.base.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat

/**
 * Extension property to get a [NetworkInfo]
 *
 * @return The active network info in the Android Framework
 */
val Context.networkInfo: NetworkInfo?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Context.getColorRes(@ColorRes colorResId: Int) = ContextCompat.getColor(this, colorResId)