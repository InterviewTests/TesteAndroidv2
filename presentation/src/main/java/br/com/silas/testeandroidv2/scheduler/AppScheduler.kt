package br.com.silas.testeandroidv2.scheduler

import br.com.silas.domain.Schedulers
import io.reactivex.rxjava3.core.Scheduler

//import io.reactivex.Scheduler

class AppScheduler : Schedulers {
    override val subscribeOn: Scheduler
        get() = io.reactivex.rxjava3.schedulers.Schedulers.io()
    override val observeOn: Scheduler
        get() = io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread()

}