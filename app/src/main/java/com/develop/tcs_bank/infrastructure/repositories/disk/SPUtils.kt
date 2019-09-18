package com.develop.tcs_bank.infrastructure.repositories.disk

import android.content.Context
import com.develop.tcs_bank.presentation.main.Constants
import com.develop.tcs_bank.presentation.main.TcsApplication
import javax.inject.Inject

class SPUtils {

    @Inject
    lateinit var context: Context

    init {
        TcsApplication.instance.componentApplication.inject(this)
    }

    companion object {
        const val USER = "user"
        const val PASS = "pass"
    }

    fun login(user: String, pass: String) {
        val settings = context.getSharedPreferences(Constants.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(USER, user)
        editor.putString(PASS, pass)
        editor.apply()
    }

    fun isLogged(): String {
        val settings = context.getSharedPreferences(Constants.PREFS_NAME, 0)
        return settings?.getString(USER, "") ?: ""
    }

    fun isLoggedPass(): String {
        val settings = context.getSharedPreferences(Constants.PREFS_NAME, 0)
        return settings?.getString(PASS, "") ?: ""
    }

    fun logoff() {
        val settings = context.getSharedPreferences(Constants.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(USER, "")
        editor.putString(PASS, "")
        editor.apply()
    }
}