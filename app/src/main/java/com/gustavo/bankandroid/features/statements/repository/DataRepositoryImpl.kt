package com.gustavo.bankandroid.features.statements.repository

import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.entity.UserStatementItem
import com.gustavo.bankandroid.features.statements.data.gson.StatementResponse
import com.gustavo.bankandroid.features.statements.data.mapper.StatementMapper
import io.reactivex.Single

class DataRepositoryImpl(
    private val serverIterator: ServerIterator,
    private val mapper: StatementMapper = StatementMapper()
) : DataRepository {
    override fun getUserStatementList(id: Int, password: String): Single<List<UserStatementItem>> {
        return serverIterator.fetchStatements(id).map { response: StatementResponse ->
            mapper.gsonToEntity(response)
        }
    }
}