package br.com.santander.android.bank.core.di

import android.content.SharedPreferences
import retrofit2.Retrofit

object Injection {

    lateinit var retrofit: Retrofit
    lateinit var preferences: SharedPreferences

    fun init(retrofit: Retrofit, preferences: SharedPreferences) {
        this.retrofit = retrofit
        this.preferences = preferences
    }

}