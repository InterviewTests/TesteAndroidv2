package com.develop.zupp_bank.infrastructure.utils

interface ResourceCallback<T> {

    fun onComplete(t: T)
}
