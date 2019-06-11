package com.zuptest.santander.login

import com.zuptest.santander.domain.business.model.Account

object LoginContract {

    interface View {
        fun displayInvalidPasswordFeedBack()
        fun launchStatementsScreen(account: Account)
        fun displayLastLogin(login: String?)
    }

    interface Presenter {
        fun doLogin(password: String, login: String)
        fun checkPreviousLogin()
    }
}