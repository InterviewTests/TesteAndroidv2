package com.develop.zupp_bank.presentation.main

import android.app.Application
import android.os.StrictMode
import com.develop.zupp_bank.di.ComponentApplication
import com.develop.zupp_bank.di.DaggerComponentApplication

class ZupApplication: Application() {
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
        lateinit var instance: ZupApplication
            private set
    }
}