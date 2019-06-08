package com.zuptest.santander.login

import com.zuptest.santander.domain.business.model.Account

object LoginContract {

    interface View {
        fun displayInvalidPasswordFeedBack()
        fun displayInvalidEmailLoginFeedBack()
        fun displayInvalidCPFLoginFeedBack()
        fun displayEmptyPasswordFeedBack()
        fun applyEmailLogin()
        fun applyCPFLogin()
        fun launchStatementsScreen(account: Account)
    }

    interface Presenter {
        fun doLogin(password: String?, login: String?)
        fun checkLoginType(charSequence: CharSequence?)
    }
}