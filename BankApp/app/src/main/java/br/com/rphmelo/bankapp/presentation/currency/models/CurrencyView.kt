package br.com.rphmelo.bankapp.presentation.currency.models

interface CurrencyView {
    fun showProgress()
    fun hideProgress()
    fun setupToolbar()
    fun setToolbarData()
    fun setToolbarError()
    fun setStatementListData()
    fun setStatementListError()
}