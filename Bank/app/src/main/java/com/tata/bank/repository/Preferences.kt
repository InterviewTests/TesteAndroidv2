package com.tata.bank.repository

import android.content.Context
import com.google.gson.Gson
import com.tata.bank.security.CipherData

// TODO This mustn't be used to store the data
object Preferences {

    private val gson = Gson()

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "com.tata.bank.prefs"
    private val C_DATA = "c_data"

    fun saveEncryptedCredentials(context: Context, cipherData: CipherData) {
        val authStr = gson.toJson(cipherData)

        val sharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.putString(C_DATA, authStr)
        editor.apply()
    }

    fun getEncryptedCredentials(context: Context): CipherData {
        val sharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val authStr = sharedPref.getString(C_DATA, "")

        return gson.fromJson(authStr, CipherData::class.java)
    }
}