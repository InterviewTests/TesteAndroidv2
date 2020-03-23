package com.example.ibm_test.localstorage

import android.content.SharedPreferences

class UserStorageImp(private val sharedPreferences: SharedPreferences) : UserStorage {

    companion object {
        const val USER_INFO: String = "user_info"
        const val USER_LOGIN = "user_login"
        const val USER_PASSWORD = "user_password"
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    override fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0F)
    }

    override fun setFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}

interface UserStorage {
    fun getString(key: String): String
    fun setString(key: String,  value: String)
    fun getInt(key: String): Int
    fun setInt(key: String,  value: Int)
    fun getFloat(key: String): Float
    fun setFloat(key: String,  value: Float)
    fun clearData()
}