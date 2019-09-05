package com.develop.zupp_bank.infrastructure.repositories.disk

import android.content.Context
import com.develop.zupp_bank.presentation.main.Constants
import com.develop.zupp_bank.presentation.main.ZupApplication
import java.util.*
import javax.inject.Inject

class SPUtils {

    @Inject
    lateinit var context: Context

    init {
        ZupApplication.instance.componentApplication.inject(this)
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
}