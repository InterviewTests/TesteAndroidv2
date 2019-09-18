package com.develop.tcs_bank.presentation.main

import android.app.Application
import android.os.StrictMode
import com.develop.tcs_bank.di.ComponentApplication
import com.develop.tcs_bank.di.DaggerComponentApplication

open class TcsApplication: Application() {
    lateinit var componentApplication: ComponentApplication
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        componentApplication = DaggerComponentApplication.builder().build()
        componentApplication.inject(this)
    }

    companion object {
         lateinit var instance: TcsApplication
            private set
    }
}