package com.androiddeveloper.santanderTest.shared.preferences

import android.content.Context

object SharedPreferenceManager {

    private const val TAG = "test_santander"

    fun setLoggedUserData(context: Context, key: SharedPreferencesEnum, isLogged: Boolean) {
        context.getSharedPreferences(TAG, 0)
            .edit()
            .putBoolean(key.value, isLogged)
            .apply()
    }

    fun isUserLogged(context: Context, key: SharedPreferencesEnum): Boolean {
        return context.getSharedPreferences(TAG, 0)
            .getBoolean(key.value, false)
    }

    fun setUsername(context: Context, key: SharedPreferencesEnum, username: String) {
        context.getSharedPreferences(TAG, 0)
            .edit()
            .putString(key.value, username)
            .apply()
    }

    fun getUsername(context: Context, key: SharedPreferencesEnum): String? {
        return context.getSharedPreferences(TAG, 0)
            .getString(key.value, "")
    }
}