package com.example.testeandroidsantander.controller

import android.util.Log
import com.example.testeandroidsantander.api.StatementApiService
import com.example.testeandroidsantander.model.Statement
import com.example.testeandroidsantander.model.StatementList
import com.example.testeandroidsantander.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementController(private val listener: StatementListener) {

    fun getStatement() {
        val callStatement = RetrofitInitializer(StatementApiService.BASE_URL).statementService()
            .getEStatement()

        callStatement.enqueue(object: Callback<StatementList> {
            override fun onResponse(call: Call<StatementList>, response: Response<StatementList>) {
                listener.onStatementAvailable(response.body() as StatementList)
            }

            override fun onFailure(call: Call<StatementList>, t: Throwable) {
                Log.e("onFailure error", t.message)
            }
        })
    }
}

interface StatementListener {

    fun onStatementAvailable(statement: StatementList)

}