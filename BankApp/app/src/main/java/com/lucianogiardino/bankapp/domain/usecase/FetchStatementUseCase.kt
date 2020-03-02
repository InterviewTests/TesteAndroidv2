package com.lucianogiardino.bankapp.domain.usecase

import com.lucianogiardino.bankapp.data.RetrofitClient
import com.lucianogiardino.bankapp.domain.model.StatementResponse
import com.lucianogiardino.bankapp.domain.model.User
import com.lucianogiardino.bankapp.ui.statement.StatementContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchStatementUseCase : StatementContract.UseCase.FetchStatement{
    override fun execute(listener: StatementContract.Presenter.OnFetchStatementResponse){
        RetrofitClient.instance.fetchStatement(User.userId).enqueue(
            object: Callback<StatementResponse> {
                override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                    listener.onFetchStatementResponseFailed("Falha ao tentar acessar o servidor")
                }
                override fun onResponse(call: Call<StatementResponse>,
                                        response: Response<StatementResponse>) {
                    listener.onFetchStatementResponseSuccess(response.body()!!.statementList)
                }
            }
        )
    }
}