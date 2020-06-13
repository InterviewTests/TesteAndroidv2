package com.qintess.santanderapp.helper

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.qintess.santanderapp.BuildConfig

interface SharedPreferencesInterface {
    fun getString(key: Prefs.Key): String?
}

object Prefs: Application(), SharedPreferencesInterface {
    enum class Key(val value: String?) {
        LAST_USER(null)
    }

    private const val PREFS_FILE_NAME = BuildConfig.APPLICATION_ID
    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = applicationContext.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: Key, value: String) {
        with(sharedPreferences.edit()) {
            putString(key.value, value)
            commit()
        }
    }

    override fun getString(key: Key): String? {
        return sharedPreferences.getString(key.value, null)
    }
}