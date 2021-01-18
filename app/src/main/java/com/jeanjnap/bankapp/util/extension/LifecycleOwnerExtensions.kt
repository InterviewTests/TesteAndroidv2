package com.jeanjnap.bankapp.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T : Any> LifecycleOwner.observe(data: LiveData<T>, function: (id: T) -> Unit) {
    data.observeSmart(this) { function.invoke(it) }
}
