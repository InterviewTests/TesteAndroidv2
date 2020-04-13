package com.example.testeandroidv2.data.repository.statement

import com.example.testeandroidv2.data.ApiService
import com.example.testeandroidv2.domain.statements.Statement
import com.example.testeandroidv2.domain.statements.StatementsBodyResponse
import retrofit2.Call
import retrofit2.Response

class StatementsApiDataSource :
    StatementsRepository {

    override fun getStatements(id: Int, statementsResultCallback: (result: StatementsResult) -> Unit) {

        ApiService.service.getStatements(id).enqueue(object: retrofit2.Callback<StatementsBodyResponse>{
            override fun onResponse(call: Call<StatementsBodyResponse>, response: Response<StatementsBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val statements: MutableList<Statement> = mutableListOf()
                        response.body()?.let { statementDetailsResponse ->
                            for (result in statementDetailsResponse.statementList) {
                                statements.add(result)
                            }
                        }

                        statementsResultCallback(
                            StatementsResult.Success(
                                statements
                            )
                        )
                    }
                    else -> statementsResultCallback(
                        StatementsResult.ApiError(
                            response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<StatementsBodyResponse>, t: Throwable) {
                statementsResultCallback(StatementsResult.ServerError)
            }
        })
    }
}