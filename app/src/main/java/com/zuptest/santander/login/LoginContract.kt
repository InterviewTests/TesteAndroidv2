package com.zuptest.santander.login

object LoginContract {

    interface View {
        fun displayInvalidPasswordFeedBack()
        fun displayInvalidEmailLoginFeedBack()
        fun displayInvalidCPFLoginFeedBack()
        fun displayEmptyPasswordFeedBack()
        fun applyEmailLogin()
        fun applyCPFLogin()
        fun launchStatementsScreen()
    }

    interface Presenter {
        fun doLogin(password: String?, login: String?)
        fun checkLoginType(charSequence: CharSequence?)
    }
}