package com.jeanjnap.bankapp

import androidx.multidex.MultiDexApplication
import com.jeanjnap.bankapp.di.AppComponent.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

@Suppress("unused")
open class BankApplication : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BankApplication)
            modules(getAllModules())
        }
    }
}
