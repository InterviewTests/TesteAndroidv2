package br.com.rphmelo.bankapp.presentation.currency.models

interface OnCurrencyLoadDataListener {
    fun onSetupToolbar()
    fun onToolbarLoadDataSuccess()
    fun onToolbarLoadDataError()
    fun onLoadStatementListSuccess()
    fun onLoadStatementListError()
}
