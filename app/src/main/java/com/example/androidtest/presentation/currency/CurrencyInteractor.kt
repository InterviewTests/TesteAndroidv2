package com.example.androidtest.presentation.currency

import com.example.androidtest.repository.AccountRepository
import com.example.androidtest.repository.UserRepository
import com.example.androidtest.util.toCurrency

interface CurrencyInteractorContract {
    fun loadUserInfo()
    fun loadPayments()
    fun requestLogoff()
}

class CurrencyInteractor(private val presenter: CurrencyPresenterContract) : CurrencyInteractorContract {

    override fun loadUserInfo() {
        AccountRepository.loggedAccount?.let {
            presenter.fillHeader(
                name = it.name,
                bankAccount = it.bankAccount,
                balance = it.balance.toCurrency()
            )
        }

    }

    override fun loadPayments() {
        presenter.fillRecentPayments(
            statements = AccountRepository.getPayments()
        )
    }

    override fun requestLogoff() {
        UserRepository.logoff()
        presenter.logoffSuccessful()
    }
}