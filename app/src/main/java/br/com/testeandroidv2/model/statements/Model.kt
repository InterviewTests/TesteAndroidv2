package br.com.testeandroidv2.model.statements

import java.text.MessageFormat

import com.google.gson.Gson

import br.com.testeandroidv2.model.enums.Endpoints
import br.com.testeandroidv2.model.statements.gson.Statement
import br.com.testeandroidv2.presenter.statements.MVP
import br.com.testeandroidv2.utils.Constantes
import br.com.testeandroidv2.utils.http.HttpAction
import br.com.testeandroidv2.utils.http.HttpCallBack
import br.com.testeandroidv2.utils.http.HttpResponse
import br.com.testeandroidv2.utils.http.HttpStatus

class Model(private val presenter: MVP.Presenter): MVP.Model {

    override fun loadList(userId: Int) {
        presenter.showProgressBar(true)

        val wsEndpoint: String = Endpoints.STATEMENTS.endpoint()
        val baseUrl: String = Constantes.baseURL + MessageFormat.format(wsEndpoint, userId.toString())

        val action = HttpAction(presenter.context)
        action.send(
            HttpStatus.GET,
            baseUrl,
            null,
            object : HttpCallBack {
                override fun onResponse(response: HttpResponse?) {
                    val result: Statement = Gson().fromJson(response?.message, Statement::class.java)

                    presenter.updateListRecycler(result.statementList)
                    presenter.showProgressBar(false)
                }

                override fun onError(response: HttpResponse?) {
                    presenter.showProgressBar(false)
                }
            })
    }
}