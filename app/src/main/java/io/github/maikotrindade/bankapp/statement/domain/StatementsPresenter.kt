package io.github.maikotrindade.bankapp.statement.domain

import io.github.maikotrindade.bankapp.base.util.SharedPrefsUtil
import io.github.maikotrindade.bankapp.statement.model.Statement
import io.github.maikotrindade.bankapp.statement.ui.StatementsFragmentInput
import java.lang.ref.WeakReference

interface StatementsPresenterInput {
    fun updateStatementsList(statements: List<Statement>)
    fun logout()
    fun showError(errorMessage: String? = null)
}

class StatementsPresenter : StatementsPresenterInput {

    lateinit var fragmentInput: WeakReference<StatementsFragmentInput>

    override fun updateStatementsList(statements: List<Statement>) {
        fragmentInput.get()?.updateStatementsList(statements)
    }

    override fun logout() {
        SharedPrefsUtil.remove(SharedPrefsUtil.userId, SharedPrefsUtil.name, SharedPrefsUtil.agency,
            SharedPrefsUtil.balance, SharedPrefsUtil.bankAccount)
        fragmentInput.get()?.redirectToLogin()
    }

    override fun showError(errorMessage: String?) {
        fragmentInput.get()?.showError(errorMessage)
    }
}