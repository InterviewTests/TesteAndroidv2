package com.gustavo.bankandroid.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes res: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(this.context).inflate(res, this, attachToRoot)