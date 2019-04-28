package br.com.alex.bankappchallenge.feature.login

import android.content.Intent
import br.com.alex.bankappchallenge.feature.statement.StatementActivity

class LoginRouter(private val activity: LoginActivity) {
    fun navigateToStatement() {
        activity.startActivity(Intent(activity, StatementActivity::class.java))
        activity.finish()
    }
}