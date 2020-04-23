package com.tata.bank.main

import com.tata.bank.login.LoginResponse
import com.tata.bank.login.UserAccount
import java.lang.ref.WeakReference

interface MainPresenterInput {
    fun handleAccountDetails(userAccount: UserAccount)

    fun presentSuccess(loginResponse: StatementResponse?)
    fun presentStatusError(code: Int)
    fun presentError(error: Throwable)
}

class MainPresenter: MainPresenterInput {
    lateinit var output: WeakReference<MainActivityInput>
    lateinit var router: MainRouterInput

    override fun handleAccountDetails(userAccount: UserAccount) {
        output.get()?.displayAccountDetails(userAccount)
    }

    override fun presentSuccess(loginResponse: StatementResponse?) {

    }

    override fun presentStatusError(code: Int) {

    }

    override fun presentError(error: Throwable) {

    }
}