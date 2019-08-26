package com.valber.santandertestvalber

import android.app.Application

class SatanderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}