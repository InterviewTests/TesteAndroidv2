package com.example.androidtest.presentation.currency

import com.example.androidtest.repository.Statement
import com.example.androidtest.repository.UserAccount
import com.example.androidtest.util.toCurrency


interface CurrencyPresenterContract {
    fun fillHeader(userData: UserAccount)
    fun fillRecentPayments(statements: List<Statement>?)
    fun logoffSuccessful()
}

class CurrencyPresenter(private val activity: CurrencyActivityContract) : CurrencyPresenterContract {

    override fun fillHeader(userData: UserAccount) {
        activity.fillTitle(userData.name)
        activity.fillBankInfo("${userData.agency} / ${userData.bankAccount}")
        activity.fillBalance(userData.balance.toCurrency())
    }

    override fun fillRecentPayments(statements: List<Statement>?) {
        activity.updateRecentPayments(statements)
    }

    override fun logoffSuccessful() {
        activity.navigateToLoginActivity()
    }
}