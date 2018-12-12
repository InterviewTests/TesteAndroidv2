package br.com.rphmelo.bankapp.currency.presentation

import br.com.rphmelo.bankapp.currency.domain.models.CurrencyView
import br.com.rphmelo.bankapp.currency.domain.models.OnCurrencyLoadDataListener
import br.com.rphmelo.bankapp.currency.domain.interactor.CurrencyInteractor
import br.com.rphmelo.bankapp.currency.domain.models.StatementResponse
import br.com.rphmelo.bankapp.login.domain.models.UserAccount

class CurrencyPresenter(private var currencyView: CurrencyView?, private val currencyInteractor: CurrencyInteractor) :
        OnCurrencyLoadDataListener {

    override fun onSetupToolbar() {
        currencyView?.setupToolbar()
    }

    override fun onSetToolbarData(userAccount: UserAccount) {
        currencyView?.apply {
            setToolbarData(userAccount)
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

    fun setupToolbar() {
        currencyInteractor.setupToolbar(this)
    }

    fun loadStatementList(userId: Int) {
        currencyView?.showProgress()
        currencyInteractor.statements(userId, this)
    }

    fun setToolbarData(userAccount: UserAccount) {
        currencyView?.showProgress()
        currencyInteractor.setToolbarData(userAccount, this)
    }

    fun onDestroy() {
        currencyView = null
    }
}