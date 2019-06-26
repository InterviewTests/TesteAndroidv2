package com.earaujo.santander.util

import android.content.Context
import android.content.SharedPreferences

class Preferences {

    companion object {

        private val SHARED_PREFERENCE_FILE = "santander"
        private val SP_USER_NAME = "user_name"
        private var sharedPreferences: SharedPreferences? = null

        fun getUserName(): String {
            return getString(SP_USER_NAME)
        }

        fun setUserName(value: String) {
            setString(SP_USER_NAME, value)
        }

        fun clearAll() {
            sharedPreferences?.edit()?.apply {
                clear()
                apply()
            }
        }

        private fun setString(key: String, value: String) {
            sharedPreferences?.edit()?.apply {
                putString(key, value)
                apply()
            }
        }

        private fun getString(key: String): String {
            return sharedPreferences?.getString(key, null) ?: ""
        }

        fun setSharePreference(context: Context) {
            if (sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences(
                    SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE
                )

            }
        }

    }

}