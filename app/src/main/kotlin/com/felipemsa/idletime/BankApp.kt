package com.felipemsa.idletime

import android.app.Application
import com.orhanobut.hawk.Hawk

class BankApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(applicationContext).build()
    }

}