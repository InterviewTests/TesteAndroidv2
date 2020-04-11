package pt.felipegouveia.bankapp.presentation

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

interface BaseSchedulerProvider {
    fun io() : Scheduler
    fun ui() : Scheduler
}

class SchedulerProvider : BaseSchedulerProvider {
    override fun io() = Schedulers.io()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}

class TrampolineSchedulerProvider : BaseSchedulerProvider {
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}

class TestSchedulerProvider(private val scheduler: TestScheduler) : BaseSchedulerProvider {
    override fun ui() = scheduler
    override fun io() = scheduler
}