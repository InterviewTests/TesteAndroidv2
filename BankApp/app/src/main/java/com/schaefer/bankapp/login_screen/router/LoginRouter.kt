package com.schaefer.bankapp.login_screen.router

import android.content.Intent
import com.schaefer.bankapp.login_screen.activity.LoginActivity
import java.lang.ref.WeakReference

class LoginRouter : CurrencyRouterInput {
    var activity: WeakReference<LoginActivity>? = null
    override fun passDataToNextScene(intent: Intent) {
        activity?.get()?.startActivity(intent)
        activity?.get()?.finish()
    }
}
