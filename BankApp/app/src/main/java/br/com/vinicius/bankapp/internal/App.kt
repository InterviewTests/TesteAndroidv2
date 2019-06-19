package br.com.vinicius.bankapp.internal

import android.app.Application

object App : Application(){

    override fun onCreate() {
        super.onCreate()
        SharedPreferences.init(this)

    }
}