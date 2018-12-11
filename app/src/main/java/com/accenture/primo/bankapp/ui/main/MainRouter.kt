package com.accenture.primo.bankapp.ui.main

import android.app.Activity
import android.content.Intent
import com.accenture.primo.bankapp.ui.login.LoginActivity
import com.accenture.primo.bankapp.ui.main.contracts.IMainRouter

class MainRouter(private val activity: Activity): IMainRouter {
    override fun showLoginScreen() {
        var intent : Intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }
}