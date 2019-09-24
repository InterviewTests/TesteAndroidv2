package com.example.santantest.domain.interactor.home

import android.util.Log
import com.example.santantest.data.AccountService
import com.example.santantest.data.Network
import com.example.santantest.domain.model.StatementResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeInteractor {

    fun getStatements(userID: Int, callback: HomeInteractorListener){
        val accountService = Network.getInstance()?.create(AccountService::class.java)
        val res = accountService?.getStatements(userID)
        res?.enqueue(object : Callback<StatementResponse>{

            override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                Log.e("ERRO",t.message)
            }

            override fun onResponse(
                call: Call<StatementResponse>,
                response: Response<StatementResponse>
            ) {
                response.body()?.statementList?.let {
                    callback.onGetStatementsSuccess(it)
                }
            }
        })



    }

}