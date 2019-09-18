package com.develop.tcs_bank.infrastructure.utils

interface ResourceCallback<T> {

    fun onComplete(t: T)
}
