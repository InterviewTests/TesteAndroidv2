package br.com.alex.bankappchallenge.interactor

import br.com.alex.bankappchallenge.interactor.mapper.StatementMapperContract
import br.com.alex.bankappchallenge.interactor.mapper.UserMapperContract
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import br.com.alex.bankappchallenge.repository.StatementRepositoryContract

class StatementInteractor(
    private val statementRepositoryContract: StatementRepositoryContract,
    private val loginRepositoryContract: LoginRepositoryContract,
    private val statementMapperContract: StatementMapperContract,
    private val userMapperContract: UserMapperContract
) : StatementInteractorContract {

    override fun fetchStatements() {
    }
}

interface StatementInteractorOutput {
    fun loadingStatements()
    fun logout()
    fun userAccountData()
    fun loadingStatementError()
}