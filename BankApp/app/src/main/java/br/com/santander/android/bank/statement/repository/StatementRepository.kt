package br.com.santander.android.bank.statement.repository

import br.com.santander.android.bank.statement.domain.model.Statements
import io.reactivex.Observable

internal class StatementRepository(private val statementService: StatementService) {

    fun getStatements(userId: Int): Observable<Statements> {
        return statementService.getStatements(userId)
    }
}