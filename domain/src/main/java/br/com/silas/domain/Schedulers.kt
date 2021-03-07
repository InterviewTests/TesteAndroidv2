package br.com.silas.domain

import io.reactivex.rxjava3.core.Scheduler


interface Schedulers {
    val subscribeOn: Scheduler
    val observeOn: Scheduler
}
