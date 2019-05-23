package com.example.testesantander

import android.app.Application
import com.example.testesantander.plataform.appComponent
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponent)
    }
}
