package br.com.rphmelo.bankapp.currency.domain.models

interface OnCurrencyLoadDataListener {
    fun onSetupToolbar()
    fun onToolbarLoadDataSuccess()
    fun onToolbarLoadDataError()
    fun onLoadStatementListSuccess(statementResponse: StatementResponse)
    fun onLoadStatementListError()
}
