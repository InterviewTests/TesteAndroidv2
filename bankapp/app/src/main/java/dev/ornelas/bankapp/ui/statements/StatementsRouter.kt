package dev.ornelas.bankapp.ui.statements

import android.content.Intent
import dev.ornelas.bankapp.ui.login.LoginActivity
import dev.ornelas.bankapp.ui.statements.StatementsActivity
import java.lang.ref.WeakReference

class StatementsRouter(val activity: WeakReference<StatementsActivity>) :
    StatementsContract.Router {

    override fun navigateToLogin() {
        activity.get()?.let {
            val intent = Intent(activity.get(), LoginActivity::class.java)
            it.startActivity(intent)
            it.finish()
        }
    }
}