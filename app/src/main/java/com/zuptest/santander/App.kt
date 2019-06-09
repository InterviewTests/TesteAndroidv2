package com.zuptest.santander

import android.app.Application
import com.zuptest.santander.data.di.DataModule
import com.zuptest.santander.di.AppModule
import com.zuptest.santander.di.PresentationModule
import com.zuptest.santander.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            this.modules(
                AppModule.module,
                PresentationModule.module,
                DataModule.module,
                DomainModule.module
            )
        }
    }
}