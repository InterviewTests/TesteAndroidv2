package com.zuptest.santander.data.repository

import com.zuptest.santander.data.remote.api.Api
import com.zuptest.santander.data.remote.mapper.mapToEntity
import com.zuptest.santander.domain.business.model.Statement
import com.zuptest.santander.domain.repository.StatementRepository
import io.reactivex.Observable

class StatementRepositoryImpl(private val api: Api) : StatementRepository {
    override fun listStatementsByAccountId(accountId: Int): Observable<List<Statement>> {
        return api.listStatementsByUserId(accountId).map {
            it.mapToEntity()
        }
    }
}