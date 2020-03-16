package dev.vitorpacheco.solutis.bankapp.statementsScreen

import android.content.Intent
import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginActivity
import java.lang.ref.WeakReference

internal interface StatementsRouterInput {
    fun navigateToLogin(): Intent
}

class StatementsRouter : StatementsRouterInput {

    var activity: WeakReference<StatementsActivity>? = null

    override fun navigateToLogin() = Intent(activity!!.get(), LoginActivity::class.java)

    companion object {
        var TAG = StatementsRouter::class.java.simpleName
    }

}