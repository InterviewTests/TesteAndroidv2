package br.com.silas.domain.statements

import br.com.silas.domain.ErrorResponse
import io.reactivex.rxjava3.core.Maybe

interface StatementsRepository {
    fun fetchStatements(userId: Int): Maybe<Pair<ErrorResponse, List<Statements>>>
}