package br.com.mdr.testeandroid.di

import android.app.Application
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val preferencesModule = module {
    single { getSharedPrefs(androidApplication()) }
    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }
}

private const val PREFERENCES_FILE_KEY = "br.com.mdr.testeandroid"
private fun getSharedPrefs(app: Application): SharedPreferences {
    return  app.getSharedPreferences(PREFERENCES_FILE_KEY,  android.content.Context.MODE_PRIVATE)
}