package br.com.rphmelo.bankapp.currency.presentation

import br.com.rphmelo.bankapp.currency.domain.models.CurrencyView
import br.com.rphmelo.bankapp.currency.domain.models.OnCurrencyLoadDataListener
import br.com.rphmelo.bankapp.currency.domain.interactor.CurrencyInteractor
import br.com.rphmelo.bankapp.currency.domain.models.StatementResponse


class CurrencyPresenter(var currencyView: CurrencyView?, val currencyInteractor: CurrencyInteractor) :
        OnCurrencyLoadDataListener {

    fun setupToolbar() {
        currencyInteractor.setupToolbar(this)
    }

    fun loadStatementList(userId: Int) {
        currencyView?.showProgress()
        currencyInteractor.statements(userId, this)
    }

    override fun onSetupToolbar() {
        currencyView?.setupToolbar()
    }

    override fun onToolbarLoadDataSuccess() {
        currencyView?.apply {
            setToolbarData()
            hideProgress()
        }
    }

    override fun onToolbarLoadDataError() {
        currencyView?.apply {
            setToolbarError()
            hideProgress()
        }
    }

    override fun onLoadStatementListSuccess(statementResponse: StatementResponse) {
        currencyView?.apply {
            setStatementListData(statementResponse)
            hideProgress()
        }
    }

    override fun onLoadStatementListError() {
        currencyView?.apply {
            setStatementListError()
            hideProgress()
        }
    }

    fun onDestroy() {
        currencyView = null
    }
}