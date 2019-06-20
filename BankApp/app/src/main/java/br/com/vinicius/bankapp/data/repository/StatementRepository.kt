package br.com.vinicius.bankapp.data.repository

import br.com.vinicius.bankapp.data.remote.ApiService
import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.data.remote.model.StatementResponse
import br.com.vinicius.bankapp.domain.StatementContract
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.internal.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementRepository : Repository(), StatementContract.IRepository {
    override fun startStatements(idUser: Int, onResult: BaseCallback<List<StatementModel>>) {
        ApiService.invoke().getStatements(idUser)
            .enqueue(object : Callback<StatementResponse> {
                override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                    t.message?.let { onResult.onUnsuccessful(it) }
                }

                override fun onResponse(call: Call<StatementResponse>, response: Response<StatementResponse>) {
                    if(response.body() == null || response.isSuccessful) onResult.onUnsuccessful(response.message())

                    response.body()?.let {
                        if(it.error.code == 0)
                            onResult.onSuccessful(it.statementList)
                        else
                            onResult.onUnsuccessful(it.error.message)
                    }
                }

            })
    }

}