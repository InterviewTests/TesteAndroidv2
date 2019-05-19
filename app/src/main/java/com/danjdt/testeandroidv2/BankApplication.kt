package com.danjdt.testeandroidv2

import android.app.Application
import android.content.Context
import com.danjdt.testeandroidv2.di.component.AppComponent
import com.danjdt.testeandroidv2.di.component.DaggerAppComponent

class BankApplication  : Application() {

    companion object {
        lateinit var instance: BankApplication
            private set
    }

    private lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerAppComponent.create()
    }

    fun getApplicationComponent(): AppComponent {
        return applicationComponent
    }

    fun getContext() : Context {
        return applicationContext
    }
}