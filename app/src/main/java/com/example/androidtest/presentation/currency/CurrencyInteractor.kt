package com.example.androidtest.presentation.currency

import com.example.androidtest.repository.ApiResponse
import com.example.androidtest.repository.Repository
import com.example.androidtest.repository.Statement
import com.example.androidtest.repository.SuccessResponse

interface CurrencyInteractorContract {
    fun loadUserInfo()
    fun requestRecentStatements()
    fun requestLogoff()
}

class CurrencyInteractor(private val presenter: CurrencyPresenterContract) : CurrencyInteractorContract {

    override fun loadUserInfo() {
        Repository.getLoggedAccount()?.let {
            presenter.fillHeader(it)
        }

    }

    override fun requestRecentStatements() {

        Repository.getRecentStatements { apiResponse: ApiResponse, statements: List<Statement>? ->
            when (apiResponse) {
                is SuccessResponse -> {
                    presenter.fillRecentPayments(statements)
                }
                else -> {
                }
            }
        }


    }

    override fun requestLogoff() {
        Repository.logoff()
        presenter.logoffSuccessful()
    }
}