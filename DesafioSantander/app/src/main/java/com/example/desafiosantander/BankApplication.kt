package com.example.desafiosantander

import android.app.Application
import com.example.desafiosantander.di.BASE_URL
import com.example.desafiosantander.di.networkModule
import com.example.desafiosantander.di.repositoryModule
import com.example.desafiosantander.di.viewModelModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BankApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        configureKoin()
        Hawk.init(this).build()
    }

    private fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BankApplication)
            modules(getModule())
            properties(mapOf(BASE_URL to BuildConfig.BASE_URL))
        }
    }

    private fun getModule() = listOf(networkModule, repositoryModule, viewModelModule)

}