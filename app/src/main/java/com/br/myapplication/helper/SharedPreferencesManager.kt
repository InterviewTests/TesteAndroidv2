package com.br.myapplication.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    var sharedPref: SharedPreferences

    init {
        sharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    fun saveUser(user: String) {
        val editor = sharedPref.edit()
        editor.putString(USER_KEY, user)
        editor.apply()
    }

    fun retrieveUser() : String? = sharedPref.getString(USER_KEY, "")

    fun clearPreferences() = sharedPref.edit().clear().apply()

    companion object {
        const val PREF_NAME = "PREF_NAME"
        const val PRIVATE_MODE = 0

        const val USER_KEY = "user"
    }
}