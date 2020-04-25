package com.tata.bank.main

import com.tata.bank.login.UserAccount
import java.lang.ref.WeakReference

interface MainPresenterInput {
    fun handleAccountDetails(userAccount: UserAccount)

    fun presentSuccess(statementResponse: StatementResponse?)
    fun presentStatusError(code: Int)
    fun presentError(error: Throwable)
    fun presentLogoutDone()
}

class MainPresenter: MainPresenterInput {
    lateinit var output: WeakReference<MainActivityInput>
    lateinit var router: MainRouterInput

    override fun handleAccountDetails(userAccount: UserAccount) {
        output.get()?.displayAccountDetails(userAccount)
    }

    override fun presentSuccess(statementResponse: StatementResponse?) {
        statementResponse?.let {
            it.error.message?.let { message ->
                output.get()?.displayError(message)
            }

            it.statementList.let { statementList ->
                output.get()?.updateStatements(statementList)
            }
        }
    }

    override fun presentStatusError(code: Int) {
        output.get()?.displayError("An error has occurred($code)")
    }

    override fun presentError(error: Throwable) {
        val message = error.message?: run { "An exception occurred" }
        output.get()?.displayError(message)
    }

    override fun presentLogoutDone() {
        router.goToLogin()
    }
}