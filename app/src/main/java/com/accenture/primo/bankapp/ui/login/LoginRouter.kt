package com.accenture.primo.bankapp.ui.login

import android.app.Activity
import android.content.Intent
import com.accenture.primo.bankapp.model.User

import com.accenture.primo.bankapp.ui.login.contracts.ILoginRouter
import com.accenture.primo.bankapp.ui.main.MainActivity
import com.accenture.primo.bankapp.EXTRA_KEY_LOGIN

class LoginRouter(private val activity: Activity): ILoginRouter {
    companion object {
        private const val LOGIN_EXTRA_KEY = EXTRA_KEY_LOGIN
    }

    override fun showNextScreen(user: User){
        var intent : Intent = Intent(activity, MainActivity::class.java)
        intent.putExtra(LOGIN_EXTRA_KEY, user)
        activity.startActivity(intent)
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.finish()
    }

}
