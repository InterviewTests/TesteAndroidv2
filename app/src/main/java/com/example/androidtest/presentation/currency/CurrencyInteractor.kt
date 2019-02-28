package com.example.androidtest.presentation.currency

import com.example.androidtest.repository.CurrencyRepository
import com.example.androidtest.repository.UserRepository

interface CurrencyInteractorContract {
    fun loadUserInfo()
    fun loadPayments()
    fun requestLogoff()
}

class CurrencyInteractor(private val presenter: CurrencyPresenterContract) : CurrencyInteractorContract {

    override fun loadUserInfo() {
        presenter.fillHeader(
            name = UserRepository.getUserName(),
            account = CurrencyRepository.getAccount(),
            balance = CurrencyRepository.getBalance()
        )
    }

    override fun loadPayments() {
        presenter.fillPaymentList(
            payments = CurrencyRepository.getPayments()
        )
    }

    override fun requestLogoff() {
        UserRepository.logoff()
        presenter.logoffSuccessful()
    }
}