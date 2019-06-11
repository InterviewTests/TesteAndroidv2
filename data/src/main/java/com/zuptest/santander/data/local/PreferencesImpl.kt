package com.zuptest.santander.data.local

import android.content.SharedPreferences

class PreferencesImpl(private val sharedPreferences: SharedPreferences) : Preferences {

    val EMAIL_KEY = "preference_key_email"

    override fun saveEmail(email: String) {
        sharedPreferences.edit()
            .putString(EMAIL_KEY, email)
            .apply()
    }

    override fun retrieveEmail(): String? {
        return sharedPreferences.getString(EMAIL_KEY, "")
    }
}