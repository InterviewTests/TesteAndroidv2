package br.com.rphmelo.bankapp.currency.domain.models

import br.com.rphmelo.bankapp.login.domain.models.UserAccount

interface CurrencyView {
    fun showProgress()
    fun hideProgress()
    fun setupToolbar()
    fun setToolbarData(userAccount: UserAccount)
    fun setStatementListData(statementResponse: StatementResponse)
    fun setStatementListError()
}