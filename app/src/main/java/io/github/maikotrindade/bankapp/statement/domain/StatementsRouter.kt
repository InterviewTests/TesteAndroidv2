package io.github.maikotrindade.bankapp.statement.domain

import androidx.navigation.NavOptions
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.base.ui.MainActivity
import java.lang.ref.WeakReference

interface StatementsRouterInput {
    fun navigateToLoginScreen()
}

class StatementsRouter : StatementsRouterInput {

    var activity: WeakReference<MainActivity>? = null

    override fun navigateToLoginScreen() {

        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(R.id.statementListFragment, false)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .build()

        activity?.get()!!.navController.navigate(R.id.loginFragment, null, options)
    }

}

