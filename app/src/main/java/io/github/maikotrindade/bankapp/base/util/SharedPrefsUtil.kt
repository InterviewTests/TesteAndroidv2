package io.github.maikotrindade.bankapp.base.util

import android.preference.PreferenceManager
import io.github.maikotrindade.bankapp.base.BankApp

object SharedPrefsUtil {

    fun save(key: String, value: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
        editor.commit()
    }

    fun save(key: String, value: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }

    fun get(key: String, defaultValue: String): String? {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        return try {
            sharedPrefs.getString(key, defaultValue)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

}