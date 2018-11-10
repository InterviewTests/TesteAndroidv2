package br.com.santander.android.bank.statement.presentation

import br.com.santander.android.bank.core.base.BasePresenter
import br.com.santander.android.bank.core.base.BaseView

interface StatementContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter
}