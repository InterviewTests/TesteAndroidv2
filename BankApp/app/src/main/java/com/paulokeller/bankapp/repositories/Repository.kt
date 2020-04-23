package com.paulokeller.bankapp.repositories

import android.content.Context

class Repository(context: Context?) {
    private val sharedPreferences = context?.getSharedPreferences("myref", Context.MODE_PRIVATE)
    private val userKey = "user"

    fun saveUser(user:String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(userKey, user)
        editor?.apply()
    }

    fun getUser(): String? {
        return sharedPreferences?.getString(userKey, null)
    }
}