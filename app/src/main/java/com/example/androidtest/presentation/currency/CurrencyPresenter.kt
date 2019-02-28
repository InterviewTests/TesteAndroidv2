package com.example.androidtest.presentation.currency

import com.example.androidtest.repository.Payment


interface CurrencyPresenterContract {
    fun fillHeader(name: String, account: String, balance: String)
    fun fillPaymentList(payments: List<Payment>)
    fun logoffSuccessful()
}

class CurrencyPresenter(private val activity: CurrencyActivityContract) : CurrencyPresenterContract {

    override fun fillHeader(name: String, account: String, balance: String) {
        activity.fillTitle(name)
        activity.fillAccount(account)
        activity.fillBalance(balance)
    }

    override fun fillPaymentList(payments: List<Payment>) {
        activity.fillPayments(payments)
    }

    override fun logoffSuccessful() {
        activity.navigateToLoginActivity()
    }
}