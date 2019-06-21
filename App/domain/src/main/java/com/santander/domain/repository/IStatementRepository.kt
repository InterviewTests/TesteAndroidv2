package com.santander.domain.repository

import com.santander.domain.entity.business.Statement
import io.reactivex.Observable

interface IStatementRepository {
    fun fetchStatements(userId: Int): Observable<List<Statement>>
}