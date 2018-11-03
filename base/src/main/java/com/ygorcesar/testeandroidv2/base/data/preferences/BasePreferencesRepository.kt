package com.ygorcesar.testeandroidv2.base.data.preferences

import android.content.SharedPreferences
import com.ygorcesar.testeandroidv2.base.data.preferences.PreferencesHelper.Companion.PREF_KEY_USER_PROFILE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasePreferencesRepository @Inject constructor(
    private val sharedPref: SharedPreferences
) {

    private fun clearUser() {
        sharedPref.edit().remove(PREF_KEY_USER_PROFILE).apply()
    }

    fun clearUserSession() {
        clearUser()
    }
}