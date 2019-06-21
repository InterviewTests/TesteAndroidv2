package com.santander.app.core.util.extension

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.defaultSchedulers(): Observable<T> = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

fun Completable.defaultSchedulers(): Completable = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())