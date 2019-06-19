package br.com.vinicius.bankapp.internal

import android.app.Application

object App : Application(){

    val sharedPreferences: SharedPreferences = SharedPreferences(this)

    override fun onCreate() {
        super.onCreate()
    }
}