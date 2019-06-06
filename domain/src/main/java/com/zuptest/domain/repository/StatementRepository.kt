package com.zuptest.domain.repository

import com.zuptest.domain.business.model.Statement
import io.reactivex.Observable

interface StatementRepository {

    fun listStatementsByAccountId(accountId: String): Observable<List<Statement>>
}