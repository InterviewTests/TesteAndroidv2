package com.zuptest.santander.domain.business.usecase.impl

import com.zuptest.santander.domain.business.model.Statement
import com.zuptest.santander.domain.business.usecase.ListStatementsUseCase
import com.zuptest.santander.domain.repository.StatementRepository
import io.reactivex.Observable

class GetStatementsUseCaseImpl(private val repository: StatementRepository) : ListStatementsUseCase {
    override fun execute(params: Int): Observable<List<Statement>> {
        return repository.listStatementsByAccountId(params)
    }
}