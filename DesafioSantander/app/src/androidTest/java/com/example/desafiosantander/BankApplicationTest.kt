package com.example.desafiosantander

import android.app.Application
import android.os.AsyncTask
import com.example.desafiosantander.di.BASE_URL
import com.example.desafiosantander.di.networkModule
import com.example.desafiosantander.di.repositoryModule
import com.example.desafiosantander.di.viewModelModule
import com.orhanobut.hawk.Hawk
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class BankApplicationTest : Application() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setInitComputationSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }

        Hawk.init(this).build()

        startKoin {
            loadKoinModules(getModule())
            properties(mapOf(BASE_URL to "http://localhost:8080/"))
        }

    }

    private fun getModule() = listOf(networkModule, repositoryModule, viewModelModule)
}