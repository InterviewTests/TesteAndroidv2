package br.com.teste.santander.spy

import android.content.Context
import br.com.teste.santander.statements.repository.StatementsRepository
import com.android.volley.Response

class StatementsRepositorySpy: StatementsRepository {

    var getStatementsCalled = false

    override fun getStatements(
        context: Context,
        userId: Int,
        succesResponseListener: Response.Listener<String>,
        errorResponseListener: Response.ErrorListener
    ) {
        getStatementsCalled = true
    }

}