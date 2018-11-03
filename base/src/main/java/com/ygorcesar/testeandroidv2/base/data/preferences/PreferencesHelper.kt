package com.ygorcesar.testeandroidv2.base.data.preferences

import android.content.SharedPreferences
import com.ygorcesar.testeandroidv2.base.extensions.empty
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PreferencesHelper @Inject constructor(private val sharedPref: SharedPreferences) {

    fun clear() {
        sharedPref.edit().clear().apply()
    }

    fun logout() {
        sharedPref.edit()
            .remove(PREF_KEY_USER_PROFILE)
            .apply()
    }

    var user: String
        get() = sharedPref.getString(PREF_KEY_USER_PROFILE, String.empty()) ?: String.empty()
        set(user) = sharedPref.edit().putString(PREF_KEY_USER_PROFILE, user).apply()

    companion object {
        const val PREF_KEY_USER_PROFILE = "user_profile"
    }
}