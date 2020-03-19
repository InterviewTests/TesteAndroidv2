package dev.ornelas.bankapp.ui.login

import android.content.Intent

interface LoginContract {
    interface View {
        fun displayLoginResult(result: LoginViewModel)
    }

    interface Presenter {
        fun onLogin(username: String, password: String)
        fun onViewCreated()
    }

    interface Router {
        fun navigateToStatements(user: LoggedInUserView)
    }

}