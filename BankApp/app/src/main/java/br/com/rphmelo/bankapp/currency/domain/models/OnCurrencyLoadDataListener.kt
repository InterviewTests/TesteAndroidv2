package br.com.rphmelo.bankapp.currency.domain.models

import br.com.rphmelo.bankapp.login.domain.models.UserAccount

interface OnCurrencyLoadDataListener {
    fun onSetupToolbar()
    fun onSetToolbarData(userAccount: UserAccount)
    fun onLoadStatementListSuccess(statementResponse: StatementResponse)
    fun onLoadStatementListError()
}
