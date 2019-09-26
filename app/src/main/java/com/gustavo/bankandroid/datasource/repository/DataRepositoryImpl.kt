package com.gustavo.bankandroid.datasource.repository

import com.gustavo.bankandroid.datasource.api.ServerIterator
import com.gustavo.bankandroid.datasource.data.statement.gson.StatementResponse
import com.gustavo.bankandroid.datasource.data.statement.mapper.StatementMapper
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.entity.UserStatementItem
import io.reactivex.Single

class DataRepositoryImpl(
    private val serverIterator: ServerIterator,
    private val mapper: StatementMapper = StatementMapper()
) : RepositoriesContract.DataRepository {
    override fun getUserStatementList(id: Int): Single<List<UserStatementItem>> {
        return serverIterator.fetchStatements(id).map { response: StatementResponse ->
            mapper.gsonToEntity(response)
        }
    }
}