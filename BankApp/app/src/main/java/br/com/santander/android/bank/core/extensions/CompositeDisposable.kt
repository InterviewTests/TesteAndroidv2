package br.com.santander.android.bank.core.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun <T> CompositeDisposable.request(request: Observable<T>,
                                    onSuccess:(T) -> Unit,
                                    onError:(Throwable) -> Unit,
                                    onTerminate:() -> Unit) {
    this.add(request.subscribeOn(Schedulers.io())
        .doOnTerminate { onTerminate() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ onSuccess(it) }, { onError(it) }))
}