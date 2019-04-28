package br.com.alex.bankappchallenge.feature.statement

import androidx.lifecycle.ViewModel
import br.com.alex.bankappchallenge.interactor.StatementInteractorContract
import br.com.alex.bankappchallenge.interactor.StatementInteractorOutput

class StatementViewModel(
    private val statementInteractorContract: StatementInteractorContract,
    private val statementReducerContract: StatementReducerContract
) : ViewModel(), StatementInteractorOutput {

    override fun loadingStatements() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun logout() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun userAccountData() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun loadingStatementError() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}