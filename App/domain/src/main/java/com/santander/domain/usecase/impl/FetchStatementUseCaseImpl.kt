package com.santander.domain.usecase.impl

import com.santander.domain.entity.business.Statement
import com.santander.domain.repository.IStatementRepository
import com.santander.domain.usecase.IFetchStatementUseCase
import io.reactivex.Observable

class FetchStatementUseCaseImpl(
    private val statementRepository: IStatementRepository
) : IFetchStatementUseCase {

    override fun execute(params: Int): Observable<List<Statement>> {
        return statementRepository.fetchStatements(params)
    }
}