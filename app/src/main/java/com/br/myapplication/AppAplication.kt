package com.br.myapplication

import android.app.Application
import com.br.myapplication.di.listModules
import org.koin.android.ext.android.startKoin

class AppAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(modules = listModules, context = this)
    }
}