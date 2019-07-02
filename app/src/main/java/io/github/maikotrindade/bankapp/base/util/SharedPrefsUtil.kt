package io.github.maikotrindade.bankapp.base.util

import android.preference.PreferenceManager
import android.util.Log
import io.github.maikotrindade.bankapp.base.BankApp



object SharedPrefsUtil {

    const val userId = "userId"
    const val name = "name"
    const val agency = "agency"
    const val bankAccount = "bankAccount"
    const val balance = "balance"

    fun save(key: String, value: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
        editor.commit()
    }

    fun save(key: String, value: Double) {
        save(key, value.toString())
    }

    fun save(key: String, value: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }

    fun get(key: String, defaultValue: Int): Int {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        return try {
            sharedPrefs.getInt(key, defaultValue)
        } catch (e: Exception) {
            Log.e(SharedPrefsUtil::class.java.simpleName, e.localizedMessage)
            defaultValue
        }
    }

    fun get(key: String, defaultValue: Double): Double {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        return try {
            val strDouble = sharedPrefs.getString(key, "")
            strDouble?.toDouble()!!
        } catch (e: Exception) {
            Log.e(SharedPrefsUtil::class.java.simpleName, e.localizedMessage)
            defaultValue
        }
    }

    fun get(key: String, defaultValue: String): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        return try {
            sharedPrefs.getString(key, defaultValue)!!
        } catch (e: Exception) {
            Log.e(SharedPrefsUtil::class.java.simpleName, e.localizedMessage)
            defaultValue
        }
    }

    fun remove(vararg key: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(BankApp.applicationContext())
        val editor = prefs.edit()
        for (removeItem in key) {
            editor.remove(removeItem)
            editor.apply()
            editor.commit()
        }
    }

}