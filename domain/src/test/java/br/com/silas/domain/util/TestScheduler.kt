package br.com.silas.domain.util

import br.com.silas.domain.Schedulers
import io.reactivex.rxjava3.core.Scheduler

class TestScheduler : Schedulers {
    override val subscribeOn: Scheduler
        get() = io.reactivex.rxjava3.schedulers.Schedulers.trampoline()
    override val observeOn: Scheduler
        get() = io.reactivex.rxjava3.schedulers.Schedulers.trampoline()
}