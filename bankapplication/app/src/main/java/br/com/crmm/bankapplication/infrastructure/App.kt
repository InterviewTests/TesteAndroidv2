package br.com.crmm.bankapplication.infrastructure

import android.app.Application
import br.com.crmm.bankapplication.infrastructure.di.module.getModulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(getModulesList())
        }
    }
}