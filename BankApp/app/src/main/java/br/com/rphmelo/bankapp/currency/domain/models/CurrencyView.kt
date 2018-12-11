package br.com.rphmelo.bankapp.currency.domain.models

interface CurrencyView {
    fun showProgress()
    fun hideProgress()
    fun setupToolbar()
    fun setToolbarData()
    fun setToolbarError()
    fun setStatementListData()
    fun setStatementListError()
}