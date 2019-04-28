package br.com.alex.bankappchallenge.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, func: (T) -> Unit) =
        observe(owner, Observer<T> { it?.let { func(it) } })