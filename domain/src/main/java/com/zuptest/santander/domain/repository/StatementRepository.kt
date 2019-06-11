package com.zuptest.santander.domain.repository

import com.zuptest.santander.domain.business.model.Statement
import io.reactivex.Observable

interface StatementRepository {

    fun listStatementsByAccountId(accountId: Int): Observable<List<Statement>>
}