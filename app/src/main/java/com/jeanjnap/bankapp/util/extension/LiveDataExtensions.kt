package com.jeanjnap.bankapp.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeSmart(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, { it?.let { source -> observer(source) } })
}
