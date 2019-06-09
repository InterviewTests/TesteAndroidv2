package com.schaefer.bankapp.login_screen.presenter

import android.content.Context
import com.schaefer.bankapp.currency_screen.activity.CurrencyActivityInput
import com.schaefer.bankapp.login_screen.interactor.CurrencyInteractorImp
import com.schaefer.bankapp.login_screen.interactor.CurrencyInteractorInput
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.statement.StatementResult

class CurrencyPresenterImp(
    var currencyActivityInput: CurrencyActivityInput,
    private val mContext: Context
) :
    CurrencyPresenterInput,
    CurrencyInteractorInput.FinishedListener {

    private var interactor: CurrencyInteractorInput = CurrencyInteractorImp()

    override fun getListStatement(userId: Int) {
        currencyActivityInput.showProgress()
        interactor.getListStatement(userId, mContext, this)
    }

    override fun successGetListStatement(result: StatementResult) {
        currencyActivityInput.hideProgress()
        currencyActivityInput.successGetListStatement(result)
    }

    override fun errorGetListStatement(errorResult: ErrorResult) {
        currencyActivityInput.hideProgress()
        currencyActivityInput.errorGetListStatement(errorResult)
    }

    override fun genericError(message: String) {
        currencyActivityInput.hideProgress()
        currencyActivityInput.errorGeneric(message)
    }

    override fun logout() {
        interactor.logout(mContext, this)
    }

    override fun successLogout() {
        currencyActivityInput.successLogout()
    }

    override fun errorLogout() {
        currencyActivityInput.errorLogout()
    }
}
