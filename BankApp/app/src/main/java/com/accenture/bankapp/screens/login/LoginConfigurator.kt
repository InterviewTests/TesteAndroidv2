package com.accenture.bankapp.screens.login

import timber.log.Timber
import java.lang.ref.WeakReference

object LoginConfigurator {
    fun configureFragment(loginFragment: LoginFragment) {
        Timber.i("configureFragment: Configuring the Login Fragment")

        val loginRouter = LoginRouter()
        loginRouter.loginFragment = WeakReference(loginFragment)
        loginRouter.loginFragmentInput = WeakReference(loginFragment)
        loginFragment.loginRouter = loginRouter
    }
}