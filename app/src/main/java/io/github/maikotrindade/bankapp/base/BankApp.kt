package io.github.maikotrindade.bankapp.base

import android.app.Application

class BankApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: BankApp? = null

        fun applicationContext(): BankApp {
            return instance as BankApp
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}