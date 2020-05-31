package com.example.bankapp

import android.app.Application
import com.example.bankapp.di.ModulosApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BankApp : Application() {

    override fun onCreate() {

        super.onCreate()
        setupKoin()

    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BankApp)
            modules(
                ModulosApp.obterModulos()
            )
        }
    }
}