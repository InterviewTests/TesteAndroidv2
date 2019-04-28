package br.com.alex.bankappchallenge.repository

import br.com.alex.bankappchallenge.network.BankAPI

class StatementRepository(
    private val bankAPI: BankAPI
) : StatementRepositoryContract {

    override fun fetchStatements(userId: Long) = bankAPI.fetchStatements(userId)
}