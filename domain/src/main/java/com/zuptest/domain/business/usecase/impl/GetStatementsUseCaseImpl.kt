package com.zuptest.domain.business.usecase.impl

import com.zuptest.domain.business.model.Statement
import com.zuptest.domain.business.usecase.GetStatementsUseCase
import com.zuptest.domain.repository.StatementRepository
import io.reactivex.Observable

class GetStatementsUseCaseImpl(private val repository: StatementRepository) : GetStatementsUseCase {
    override fun execute(params: String): Observable<List<Statement>> {
        return repository.listStatementsByAccountId(params)
    }
}