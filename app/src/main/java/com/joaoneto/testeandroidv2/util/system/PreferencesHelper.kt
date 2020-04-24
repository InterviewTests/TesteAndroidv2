package com.joaoneto.testeandroidv2.util.system

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(var context: Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences("testeAndroid2", Context.MODE_PRIVATE)
    private var editor = preferences.edit()

    fun getUsername(): String {
        return preferences.getString("username", "") as String
    }

    fun setUsername(username: String) {
        editor.putString("username", username)
        editor.commit()
    }

    fun getPassword(): String {
        return preferences.getString("password", "") as String
    }

    fun setPassword(password: String) {
        editor.putString("password", password)
        editor.commit()
    }
}