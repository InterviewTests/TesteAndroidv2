package br.com.silas.domain

import io.reactivex.rxjava3.core.Single

abstract class InteractorSingle<T, R> internal constructor(private val schedulers: Schedulers) {
    protected abstract fun create(request: R): Single<T>

    fun execute(request: R): Single<T> {
        return create(request)
            .subscribeOn(schedulers.subscribeOn)
            .observeOn(schedulers.observeOn)
    }

    abstract class Request
}