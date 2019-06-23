package com.earaujo.santander.login

import android.content.Intent
import com.earaujo.santander.repository.models.UserAccountModel
import com.earaujo.santander.statements.StatementsActivity
import java.lang.ref.WeakReference

class LoginRouter : LoginRouterInput {

    var activity: WeakReference<LoginActivity>? = null

    fun passDataToNextScene(intent: Intent) {
        activity?.get()?.let {
            val userData = it.userAccount
            intent.putExtra(StatementsActivity.INTENT_USER_DATA, userData)
        }
    }

    override fun startStatementScreen() {
        activity?.get()?.let {
            val intent = Intent(it, StatementsActivity::class.java)
            passDataToNextScene(intent)
            it.startActivity(intent)
        }
    }

}

interface LoginRouterInput {
    fun startStatementScreen()
}