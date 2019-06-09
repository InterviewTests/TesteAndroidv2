package com.schaefer.bankapp

import android.content.Context
import com.schaefer.bankapp.login_screen.interactor.CurrencyInteractorInput
import com.schaefer.bankapp.model.statement.StatementModel
import com.schaefer.bankapp.model.statement.StatementResult

class StatementInteractorSpy : CurrencyInteractorInput {
    override fun getListStatement(
        userId: Int,
        context: Context,
        finishedListener: CurrencyInteractorInput.FinishedListener
    ) {
        val result = StatementResult()
        result.list = arrayListOf()
        result.list.add(StatementModel("Teste 01", "Teste pagamento", "2019-06-09", 1000F))
        result.list.add(StatementModel("Teste 02", "Teste pagamento", "2019-06-09", 2000F))
        finishedListener.successGetListStatement(result)
    }

    override fun logout(context: Context, finishedListener: CurrencyInteractorInput.FinishedListener) {
        finishedListener.successLogout()
    }
}