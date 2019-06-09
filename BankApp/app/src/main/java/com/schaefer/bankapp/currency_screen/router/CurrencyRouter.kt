package com.schaefer.bankapp.login_screen.router

import android.content.Intent
import com.schaefer.bankapp.currency_screen.activity.CurrencyActivity
import java.lang.ref.WeakReference

class CurrencyRouter : CurrencyRouterInput {
    var activity: WeakReference<CurrencyActivity>? = null
    override fun passDataToNextScene(intent: Intent) {
        activity?.get()?.startActivity(intent)
        activity?.get()?.finish()
    }
}
