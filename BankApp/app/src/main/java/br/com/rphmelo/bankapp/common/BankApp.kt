package br.com.rphmelo.bankapp.common

import android.app.Application
import com.facebook.stetho.Stetho

class BankApp() : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}