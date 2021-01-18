package com.jeanjnap.bankapp.util.extension

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getContextCompactDrawable(@DrawableRes drawableId: Int) = ContextCompat.getDrawable(this, drawableId)