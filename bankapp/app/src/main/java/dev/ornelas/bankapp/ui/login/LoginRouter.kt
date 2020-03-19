package dev.ornelas.bankapp.ui.login

import android.content.Intent
import dev.ornelas.bankapp.ui.statements.StatementsActivity
import java.lang.ref.WeakReference

class LoginRouter(val activity: WeakReference<LoginActivity>) : LoginContract.Router {

    companion object {
        const val APP_USER_INTENT = "APP_USER_INTENT"
    }

    override fun navigateToStatements(user: UserViewModel) {
        val intent = Intent(activity.get(), StatementsActivity::class.java)
        intent.putExtra(APP_USER_INTENT, user)

        activity.get()?.let {
            it.startActivity(intent)
            it.finish()
        }
    }
}