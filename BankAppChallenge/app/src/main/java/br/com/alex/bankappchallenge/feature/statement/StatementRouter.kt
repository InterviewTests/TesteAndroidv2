package br.com.alex.bankappchallenge.feature.statement

import android.content.Intent
import br.com.alex.bankappchallenge.feature.login.LoginActivity

class StatementRouter(private val activity: StatementActivity) {
    fun navigateBackToLogin() {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }
}