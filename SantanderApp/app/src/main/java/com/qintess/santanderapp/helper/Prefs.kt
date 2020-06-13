package com.qintess.santanderapp.helper

import android.content.Context
import android.content.SharedPreferences
import com.qintess.santanderapp.BuildConfig

class Prefs(ctx: Context) {
    enum class Key(val value: String?) {
        LAST_USER(null)
    }

    private val PREFS_FILE_NAME = BuildConfig.APPLICATION_ID
    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = ctx.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: Key, value: String) {
        with(sharedPreferences.edit()) {
            putString(key.value, value)
            commit()
        }
    }

    fun getString(key: Key): String? {
        return sharedPreferences.getString(key.value, null)
    }
}