package br.com.santander.android.bank.statement.domain.interactor

import br.com.santander.android.bank.statement.domain.model.Statements
import br.com.santander.android.bank.statement.repository.StatementRepository
import io.reactivex.Observable

internal class StatementInteractor(private val statementRepository: StatementRepository) {

    fun getStatements(userId: Int): Observable<Statements> {
        return statementRepository.getStatements(userId)
    }
}