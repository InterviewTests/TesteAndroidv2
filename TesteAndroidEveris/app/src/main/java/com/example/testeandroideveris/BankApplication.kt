package com.example.testeandroideveris

import android.app.Application
import com.example.testeandroideveris.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BankApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BankApplication)
            modules(appModules)
        }
    }
}