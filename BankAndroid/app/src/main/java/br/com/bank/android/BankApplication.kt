package br.com.bank.android

import android.app.Application
import br.com.bank.android.infra.BankPreferences

class BankApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BankPreferences.init(this)
    }
}