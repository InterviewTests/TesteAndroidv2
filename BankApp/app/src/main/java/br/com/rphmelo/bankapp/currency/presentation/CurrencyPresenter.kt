package br.com.rphmelo.bankapp.currency.presentation

import br.com.rphmelo.bankapp.currency.domain.models.CurrencyView
import br.com.rphmelo.bankapp.currency.domain.models.OnCurrencyLoadDataListener
import br.com.rphmelo.bankapp.currency.domain.interactor.CurrencyInteractor


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