package com.example.androidtest.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {

    private var PRIVATE_MODE = 0


    private lateinit var sharedPref: SharedPreferences

    fun get(context: Context, prefName: String): String {
        sharedPref = context.getSharedPreferences(prefName, PRIVATE_MODE)
        val res = sharedPref.getString(prefName, "")
        return res.toString()
    }

    fun post(context: Context, text: String, prefName: String) {
        sharedPref = context.getSharedPreferences(prefName, PRIVATE_MODE)
        val edit = sharedPref.edit()
        edit.putString(prefName, text)
        edit.apply()
    }
}