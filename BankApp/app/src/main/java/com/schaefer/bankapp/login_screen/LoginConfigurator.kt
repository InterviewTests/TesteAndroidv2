package com.schaefer.bankapp.login_screen

import com.schaefer.bankapp.login_screen.activity.LoginActivity
import com.schaefer.bankapp.login_screen.presenter.LoginPresenterImp
import com.schaefer.bankapp.login_screen.router.LoginRouter
import java.lang.ref.WeakReference

enum class LoginConfigurator {
    INSTANCE;

    fun configure(activity: LoginActivity) {
        val router = LoginRouter()
        router.activity = WeakReference<LoginActivity>(activity)

        val presenter = LoginPresenterImp(activity, activity.applicationContext)
        presenter.loginActivityInput = activity

        if (activity.router == null) {
            activity.router = router
        }

        if (activity.presenter == null) {
            activity.presenter = presenter
        }
    }
}