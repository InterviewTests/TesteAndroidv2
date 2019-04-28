package br.com.alex.bankappchallenge.repository

import br.com.alex.bankappchallenge.network.model.StatementResponse
import io.reactivex.Single

interface StatementRepositoryContract {

    fun fetchStatements(userId: Long): Single<StatementResponse>
}