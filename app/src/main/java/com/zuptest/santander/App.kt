package com.zuptest.santander

import android.app.Application
import com.zuptest.data.di.DataModule
import com.zuptest.domain.di.DomainModule
import com.zuptest.santander.di.AppModule
import com.zuptest.santander.di.PresentationModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            this.modules(
                AppModule.module,
                PresentationModule.module,
                DataModule.module,
                DomainModule.module
            )
        }
    }
}