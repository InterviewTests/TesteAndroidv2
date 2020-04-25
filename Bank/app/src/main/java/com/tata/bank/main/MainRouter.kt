package com.tata.bank.main

import android.content.Intent
import com.tata.bank.login.LoginActivity
import java.lang.ref.WeakReference

interface MainRouterInput {
    fun goToLogin()

}

class MainRouter: MainRouterInput {
    lateinit var activity: WeakReference<MainActivity>

    override fun goToLogin() {
        val intent = Intent(activity.get()?.baseContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.get()?.startActivity(intent)
    }
}