package br.com.santander.android.bank.core

import android.app.Application
import android.preference.PreferenceManager
import br.com.santander.android.bank.core.di.Injection


class BankApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initInjection()
    }

    private fun initInjection() {
        Injection.init(
            retrofit = BankApi.retrofit(),
            preferences = PreferenceManager.getDefaultSharedPreferences(this)
        )
    }
}