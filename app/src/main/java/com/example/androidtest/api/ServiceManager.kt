package com.example.androidtest.api

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//@SuppressLint("CheckResult")
//fun <T> Observable<T>.serviceCall(onResponse: (T) -> Unit, onThrow: (Throwable) -> Unit) {
//    observeOn(AndroidSchedulers.mainThread())
//        .subscribeOn(Schedulers.io())
//        .subscribe(onResponse, onThrow)
//}

@SuppressLint("CheckResult")
fun <T> Observable<T>.serviceCall(onResponse: (T) -> Unit, onThrow: (Throwable) -> Unit) {
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({
            try {
                onResponse(it)
            } catch (e: Exception) {
                onThrow(ServiceResponseException(e))
            }
        }, {
            onThrow(it)
        })
}

class ServiceResponseException(cause: Throwable? = null) : Throwable(cause)