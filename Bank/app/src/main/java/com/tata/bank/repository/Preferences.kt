package com.tata.bank.repository

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import com.tata.bank.security.CipherData

// TODO This mustn't be used to store the data
class Preferences(@NonNull private val context: Context) {

    private val gson = Gson()

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "com.tata.bank.prefs"
    private val C_DATA = "c_data"

    fun saveEncryptedCredentials(cipherData: CipherData) {
        val authStr = gson.toJson(cipherData)

        val sharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.putString(C_DATA, authStr)
        editor.apply()
    }

    fun getEncryptedCredentials(): CipherData {
        val sharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val authStr = sharedPref.getString(C_DATA, "")

        return gson.fromJson(authStr, CipherData::class.java)
    }
}