package br.com.teste.santander.statements.interactor

import android.content.Context
import br.com.teste.santander.model.Statement
import br.com.teste.santander.statements.presenter.StatementsPresenter
import br.com.teste.santander.statements.repository.StatementsRepository
import br.com.teste.santander.statements.repository.model.StatementsResponse
import com.android.volley.Response
import com.google.gson.Gson

class StatementsInteractorImpl: StatementsInteractor {

    var presenter: StatementsPresenter? = null
    var repository: StatementsRepository? = null

    override fun getStatements(context: Context, userId: Int) {
        repository?.getStatements(
            context,
            userId,
            Response.Listener { response ->
                val statementsResponse = Gson().fromJson(response, StatementsResponse::class.java)

                if (statementsResponse.error?.message != null) {
                    presenter?.onRequestStatementsError(statementsResponse.error.message)
                } else {
                    presenter?.onRequestStatementsSuccess(statementsResponse.statementList ?: ArrayList())
                }
            },
            Response.ErrorListener {
                presenter?.onRequestStatementsError("Erro ao obter lançamentos, tente novamente mais tarde.")
            })
    }

}