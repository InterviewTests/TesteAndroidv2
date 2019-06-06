package com.zuptest.data.repository

import com.zuptest.data.remote.api.Api
import com.zuptest.domain.business.model.Statement
import com.zuptest.domain.repository.StatementRepository
import io.reactivex.Observable

class StatementRepositoryImpl(private val api: Api): StatementRepository {
    override fun listStatementsByAccountId(accountId: String): Observable<List<Statement>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}