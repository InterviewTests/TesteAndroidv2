package br.com.santander.android.bank.statement.repository

import br.com.santander.android.bank.statement.domain.model.Statements
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

internal interface StatementApi {

    companion object {
        private const val PARAM_USER_ID = "userId"
        private const val STATEMENTS = "/api/statements/{$PARAM_USER_ID}"
    }

    @GET(STATEMENTS)
    fun getStatements(@Path(PARAM_USER_ID) userId: Int): Observable<Statements>
}