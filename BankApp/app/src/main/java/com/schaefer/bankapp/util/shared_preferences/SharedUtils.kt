package com.schaefer.bankapp.util.shared_preferences

import android.content.Context
import org.json.JSONObject

class SharedUtils {
    companion object {
        @JvmStatic
        fun loadShared(nameOfShared: String, nameOfKey: String, myApplicationContext: Context): String? {
            val pSharedPref = myApplicationContext.getSharedPreferences(nameOfShared, Context.MODE_PRIVATE)
            try {
                if (pSharedPref != null) {
                    return pSharedPref.getString(nameOfKey, (JSONObject()).toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        @JvmStatic
        fun saveShared(
            value: String,
            nameOfShared: String,
            nameOfKey: String,
            myApplicationContext: Context
        ) {
            val pSharedPref: android.content.SharedPreferences? =
                myApplicationContext.getSharedPreferences(nameOfShared, Context.MODE_PRIVATE)
            val editor = pSharedPref?.edit()
            editor?.remove(nameOfKey)?.apply()
            editor?.putString(nameOfKey, value)
            editor?.commit()
        }

        @JvmStatic
        fun deleteSharedPreference(nameOfShared: String, myApplicationContext: Context) {
            val preferences = myApplicationContext.getSharedPreferences(nameOfShared, Context.MODE_PRIVATE)
            preferences.edit().clear().apply()
        }
    }
}