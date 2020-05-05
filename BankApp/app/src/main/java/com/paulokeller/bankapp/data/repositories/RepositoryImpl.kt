package com.paulokeller.bankapp.data.repositories

import android.content.Context

class RepositoryImpl(context: Context?) : Repository {
    private val sharedPreferences = context?.getSharedPreferences("myref", Context.MODE_PRIVATE)
    private val userKey = "user"

    override fun saveUser(user: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(userKey, user)
        editor?.apply()
    }

    override fun getUser(): String? {
        return sharedPreferences?.getString(userKey, null)
    }
}