package br.com.santander.android.bank.statement.repository

import br.com.santander.android.bank.core.di.Injection
import br.com.santander.android.bank.statement.domain.model.Statements
import io.reactivex.Observable

internal class StatementService : StatementApi {

    private val statementApi by lazy { Injection.retrofit.create(StatementApi::class.java) }

    override fun getStatements(userId: Int): Observable<Statements> {
        return statementApi.getStatements(userId)
    }

}