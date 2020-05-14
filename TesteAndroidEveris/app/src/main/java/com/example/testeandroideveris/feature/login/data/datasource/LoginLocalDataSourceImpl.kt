package com.example.testeandroideveris.feature.login.data.datasource

import android.content.Context
import android.content.SharedPreferences

class LoginLocalDataSourceImpl(context: Context) : LoginLocalDataSource {

    private val PREFS_NAME = "everisbank"
    private val USER_NAME = "USER_NAME"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveUser(user: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit().apply {
            putString(USER_NAME, user)
        }
        editor.apply()
    }

    override fun getUser(): String? {
        return sharedPref.getString(USER_NAME, "")
    }
}