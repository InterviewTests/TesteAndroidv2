package com.schaefer.bankapp.currency_screen.activity

import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.statement.StatementResult

interface CurrencyActivityInput {
    fun showProgress()
    fun hideProgress()
    fun errorGeneric(message: String)
    fun successGetListStatement(result: StatementResult)
    fun errorGetListStatement(error: ErrorResult)
    fun successLogout()
    fun errorLogout()
}