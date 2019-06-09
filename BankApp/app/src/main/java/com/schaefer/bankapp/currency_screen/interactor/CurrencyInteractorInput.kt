package com.schaefer.bankapp.login_screen.interactor

import android.content.Context
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.statement.StatementResult

interface CurrencyInteractorInput {
    fun getListStatement(userId: Int, context: Context, finishedListener: FinishedListener)
    fun logout(context: Context, finishedListener: FinishedListener)

    interface FinishedListener {
        fun successGetListStatement(result: StatementResult)
        fun errorGetListStatement(errorResult: ErrorResult)
        fun genericError(message: String)
        fun successLogout()
        fun errorLogout()
    }
}