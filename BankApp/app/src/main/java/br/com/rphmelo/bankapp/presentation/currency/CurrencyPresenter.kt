package br.com.rphmelo.bankapp.presentation.currency

import br.com.rphmelo.bankapp.presentation.currency.models.CurrencyView
import br.com.rphmelo.bankapp.presentation.currency.models.OnCurrencyLoadDataListener


class CurrencyPresenter(var currencyView: CurrencyView?, val currencyInteractor: CurrencyInteractor) :
        OnCurrencyLoadDataListener {

    fun setupToolbar() {
        currencyInteractor.setupToolbar(this)
    }

    fun toolbarLoadData(statusCode: Int) {
        currencyInteractor.loadToolbarData(statusCode, this)
    }

    fun statementLoadData(statusCode: Int) {
        currencyInteractor.loadStatementData(statusCode, this)
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

    override fun onLoadStatementListSuccess() {
        currencyView?.apply {
            setStatementListData()
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