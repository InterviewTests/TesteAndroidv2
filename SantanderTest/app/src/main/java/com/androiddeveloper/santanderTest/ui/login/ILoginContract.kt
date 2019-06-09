package com.androiddeveloper.santanderTest.ui.login

import com.androiddeveloper.santanderTest.data.model.userdata.LoginError

interface ILoginContract {

    interface LoginActInput {
        fun onLoginError(err: LoginError)
        fun onLoginSuccess()
        fun onInvalidLogin(message: Int)
        fun onValidLogin(username: String, password: String)
    }

    interface LoginPresenterInput {
        fun isUserValid(username: String, password: String)
    }

    interface LoginInteractor {
        fun bind(activity: LoginActivity)
        fun fetchUserData(username: String, password: String)
        fun isUserValid(username: String, password: String)
    }
}