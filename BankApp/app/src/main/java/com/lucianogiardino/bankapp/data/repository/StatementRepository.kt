package com.lucianogiardino.bankapp.data.repository

import com.lucianogiardino.bankapp.data.RetrofitClient
import com.lucianogiardino.bankapp.domain.model.StatementResponse
import com.lucianogiardino.bankapp.domain.model.User
import com.lucianogiardino.bankapp.presentation.statement.StatementContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementRepository: StatementContract.Repository {

    override fun fetchStatement(useCase: StatementContract.UseCase.OnFetchStatementResponse) {
        RetrofitClient.instance.fetchStatement(User.userId).enqueue(
            object: Callback<StatementResponse> {
                override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                    useCase.onFetchStatementResponseFailed("Falha ao tentar acessar o servidor")
                }
                override fun onResponse(call: Call<StatementResponse>,
                                        response: Response<StatementResponse>
                ) {
                    useCase.onFetchStatementResponseSuccess(response.body()!!.statementList)
                }
            }
        )
    }
}