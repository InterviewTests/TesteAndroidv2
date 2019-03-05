package com.example.androidtest.presentation.currency

import android.content.Context
import com.example.androidtest.repository.ApiResponse
import com.example.androidtest.repository.Repository
import com.example.androidtest.repository.Statement
import com.example.androidtest.repository.SuccessResponse

interface CurrencyInteractorContract {
    fun loadUserInfo()
    fun requestRecentStatements()
    fun requestLogoff()
}

class CurrencyInteractor(
    private val context: Context,
    private val presenter: CurrencyPresenterContract
) : CurrencyInteractorContract {

    override fun loadUserInfo() {
        presenter.requestingRecentStatements()
        Repository.getLoggedAccount(context)?.let {
            presenter.fillHeader(it)
        }
    }

    override fun requestRecentStatements() {
        Repository.getRecentStatements(context) { apiResponse: ApiResponse, statements: List<Statement>? ->
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
        Repository.logoff(context)
        presenter.logoffSuccessful()
    }
}