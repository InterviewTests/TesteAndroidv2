package br.com.silas.domain

import io.reactivex.rxjava3.core.Completable

abstract class InteractorCompletable<R> internal constructor(private val schedulers: Schedulers) {
    protected abstract fun create(request: R): Completable

    fun execute(request: R): Completable {
        return create(request)
            .subscribeOn(schedulers.subscribeOn)
            .observeOn(schedulers.observeOn)
    }

    abstract class Request
}