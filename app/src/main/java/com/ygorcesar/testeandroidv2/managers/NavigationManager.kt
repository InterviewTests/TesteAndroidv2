package com.ygorcesar.testeandroidv2.managers

import android.content.Context
import com.ygorcesar.testeandroidv2.home.presentation.HomeActivityArgs
import com.ygorcesar.testeandroidv2.login.presentation.LoginActivityArgs

object NavigationManager {

    fun navigateToLogin(context: Context?) {
        context?.let {
            LoginActivityArgs().launch(
                context = context,
                finishCurrent = true
            )
        }
    }

    fun logout(context: Context?) {
        SessionManager.logout()
        navigateToLogin(context)
    }

    fun navigateToHome(context: Context?) {
        context?.let {
            HomeActivityArgs().launch(
                context = context,
                finishCurrent = true
            )
        }
    }
}