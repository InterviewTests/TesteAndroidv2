package com.jisellemartins.bank

import android.app.Application
import com.jisellemartins.bank.di.moduleGlobal
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class BankAplication:Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@BankAplication)
            modules(moduleGlobal)
        }
    }
}