package com.santander.app

import android.app.Application
import com.santander.app.core.di.PresenterModule
import com.santander.data.di.RepositoryModule
import com.santander.domain.di.UseCaseModule
import org.koin.android.ext.android.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(RepositoryModule.module, UseCaseModule.module, PresenterModule.module)
        )
    }
}