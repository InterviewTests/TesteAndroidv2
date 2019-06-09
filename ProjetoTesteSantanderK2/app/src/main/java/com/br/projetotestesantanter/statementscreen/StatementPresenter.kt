package com.br.projetotestesantanter.statementscreen

import com.br.projetotestesantanter.R
import com.br.projetotestesantanter.Utils
import com.br.projetotestesantanter.api.Endpoint
import com.br.projetotestesantanter.api.RetrofitConfiguration
import com.br.projetotestesantanter.api.model.LoginResponse
import com.br.projetotestesantanter.api.model.StatementListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementPresenter : StatementContract.Presenter {

    var view: StatementContract.View? = null

    var loginResponse: LoginResponse? = null

    override fun resultLogin(loginResponse: LoginResponse) {

        this.loginResponse = loginResponse
    }


    override fun attachView(view: StatementContract.View) {
        this.view = view
    }


    override fun start() {
        /*if (this.loginResponse == null || this.loginResponse!!.userAccount == null) {

            this.view?.showErroMsg("Erro ao carregar as informacoes")
            this.view?.hiddenProgressBar()
            return
        }

        loadInfoPayment()
        this.view?.dataHeader(loginResponse!!)*/

    }

    override fun detachView() {
        this.view = null
    }

    private fun loadInfoPayment() {

        if (view?.getContext()?.let { Utils.isConected(it) }!!) {


            val retrofitClient = RetrofitConfiguration
                .getRetrofitInstance()

            val endpoint = retrofitClient.create(Endpoint::class.java)

            val call = loginResponse?.userAccount?.userId?.let { endpoint.getStatements(it) }

            call?.enqueue(object : Callback<StatementListResponse> {
                override fun onFailure(call: Call<StatementListResponse>, t: Throwable) {
                    t.message?.let { view?.showErroMsg(it) }
                    view?.hiddenProgressBar()
                }

                override fun onResponse(call: Call<StatementListResponse>, response: Response<StatementListResponse>) {

                    response.body()?.let { view?.listStatement(it) }
                }

            })
        } else {
            this.view?.showErroMsg(this.view!!.getContext().getString(R.string.error_not_internet))

        }


    }
}