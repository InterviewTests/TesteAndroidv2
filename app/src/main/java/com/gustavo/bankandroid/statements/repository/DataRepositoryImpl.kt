package com.gustavo.bankandroid.statements.repository

import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.statements.data.gson.StatementResponse
import io.reactivex.Single

class DataRepositoryImpl(private val serverIterator: ServerIterator) : DataRepository {
    override fun getUserStatementList(id: Int, password: String): Single<StatementResponse> {
        return serverIterator.fetchStatements(id)
    }
}