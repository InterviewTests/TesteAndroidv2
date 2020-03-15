package dev.vitorpacheco.solutis.bankapp.loginScreen

import android.content.Intent
import java.lang.ref.WeakReference

internal interface LoginRouterInput {
    fun navigateToStatements(account: UserAccount): Intent
}

class LoginRouter : LoginRouterInput {

    lateinit var activity: WeakReference<LoginActivity>

    override fun navigateToStatements(account: UserAccount): Intent {
//        val intent = Intent(activity.get(), StatementsActivity::class.java)
//        intent.putExtra("account", account)
//
//        return intent
        return Intent()
    }

    companion object {
        var TAG = LoginRouter::class.java.simpleName
    }

}