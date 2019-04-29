package br.com.alex.bankappchallenge.interactor

interface StatementInteractorContract {
    fun statementInteractorOutputImpl(statementInteractorOutput: StatementInteractorOutput)
    fun fetchStatements()
    fun fetchUserAccount()
    fun logout()
    fun clear()
}