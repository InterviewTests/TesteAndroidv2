package com.qintess.santanderapp.ui.view.statementsScreen

import com.qintess.santanderapp.model.StatementModel
import java.lang.ref.WeakReference

interface StatementsPresenterInput {
    fun presentStatements(statements: ArrayList<StatementModel>)
    fun presentErrorMessage(title: String, msg: String)
}

class StatementsPresenter: StatementsPresenterInput {
    var output: WeakReference<StatementsActivityInput>? = null

    override fun presentStatements(statements: ArrayList<StatementModel>) {
        output?.get()?.updateList(statements)
    }

    override fun presentErrorMessage(title: String, msg: String) {
        output?.get()?.showAlert(title, msg)
    }
}