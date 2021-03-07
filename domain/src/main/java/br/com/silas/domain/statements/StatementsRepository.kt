package br.com.silas.domain.statements

import br.com.silas.domain.ErrorResponse
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface StatementsRepository {
    fun fetchStatements(userId: Int): Single<Pair<ErrorResponse, List<Statements>>>
}