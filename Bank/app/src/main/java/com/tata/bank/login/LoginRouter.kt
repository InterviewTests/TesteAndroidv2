package com.tata.bank.login

import android.content.Intent
import com.tata.bank.main.MainActivity
import java.lang.ref.WeakReference


interface LoginRouterInput {
    fun goToMain(userAccount: UserAccount)
}

class LoginRouter: LoginRouterInput {

    lateinit var activity: WeakReference<LoginActivity>

    override fun goToMain(userAccount: UserAccount) {
        val intent = Intent(activity.get()?.baseContext, MainActivity::class.java)
        intent.putExtra("user_extra", userAccount)
        activity.get()?.startActivity(intent)
    }
}