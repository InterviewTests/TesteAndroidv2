package com.example.androidtest.presentation.currency

import com.example.androidtest.repository.Statement


interface CurrencyPresenterContract {
    fun fillHeader(name: String, bankAccount: String, balance: String)
    fun fillRecentPayments(statements: List<Statement>)
    fun logoffSuccessful()
}

class CurrencyPresenter(private val activity: CurrencyActivityContract) : CurrencyPresenterContract {

    override fun fillHeader(name: String, bankAccount: String, balance: String) {
        activity.fillTitle(name)
        activity.fillAccount(bankAccount)
        activity.fillBalance(balance)
    }

    override fun fillRecentPayments(statements: List<Statement>) {
        activity.updateRecentPayments(statements)
    }

    override fun logoffSuccessful() {
        activity.navigateToLoginActivity()
    }
}