package com.example.bankapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

private val PREF_LOGIN = "LOGIN"
private var PRIVATE_MODE = 0

class SharedPrefs(val context: Context) {
    fun getLogin(): String{
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_LOGIN, PRIVATE_MODE)
        return sharedPref.getString(PREF_LOGIN, "")
    }

    fun setLogin(login: String){
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_LOGIN, PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.putString(PREF_LOGIN, login)
        editor.apply()
    }

    fun logout(){
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_LOGIN, PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.putString(PREF_LOGIN, "")
        editor.apply()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: SharedPrefs? = null

        fun getInstance(context: Context): SharedPrefs {
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SharedPrefs(context).also { INSTANCE = it }
            }
            return INSTANCE!!
        }
    }
}