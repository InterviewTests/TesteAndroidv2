package com.santander.data.repository

import com.santander.data.mapper.StatementMapper
import com.santander.data.source.remote.API
import com.santander.domain.entity.business.Statement
import com.santander.domain.repository.IStatementRepository
import io.reactivex.Observable

class StatementRepositoryImpl(private val api: API) : IStatementRepository {

    override fun fetchStatements(userId: Int): Observable<List<Statement>> {
        return api.fetchStatements(userId = userId)
            .map { StatementMapper().toEntity(it.statementList) }
    }

}