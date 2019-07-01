package io.github.maikotrindade.bankapp.login

import android.os.Bundle
import androidx.navigation.NavOptions
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.base.ui.MainActivity
import io.github.maikotrindade.bankapp.login.model.UserData
import java.lang.ref.WeakReference

interface LoginRouterInput {
    fun navigateToStatementScreen(userAccount: UserData)
}

class LoginRouter : LoginRouterInput {

    var activity: WeakReference<MainActivity>? = null
    companion object {
        const val navLoginStatements = "navLoginStatements_bundle"
    }

    override fun navigateToStatementScreen(userAccount: UserData) {

        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(R.id.statementListFragment, false)
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        val args = Bundle()
        args.putParcelable(navLoginStatements, userAccount)

        activity?.get()!!.navController.navigate(R.id.statementListFragment, args, options)
    }

}

