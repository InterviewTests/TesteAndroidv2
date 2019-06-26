package com.earaujo.santander.statements

import android.content.Intent
import com.earaujo.santander.login.LoginActivity
import java.lang.ref.WeakReference

class StatementsRouter : StatementsRouterInput {

    var activity: WeakReference<StatementsActivity>? = null

    override fun logout() {
        activity?.get()?.let {
            val intent = Intent(it, LoginActivity::class.java)
            it.startActivity(intent)
            it.finish()
        }
    }
}

interface StatementsRouterInput {
    fun logout()
}