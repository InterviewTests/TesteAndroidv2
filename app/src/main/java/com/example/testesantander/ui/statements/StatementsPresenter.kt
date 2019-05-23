package com.example.testesantander.ui.statements

import com.example.testesantander.domain.model.StatementsData
import com.example.testesantander.domain.model.StatementsResponse
import com.example.testesantander.domain.usecase.IGetStatementsUseCase
import com.example.testesantander.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementsPresenter(private val getStatementsUseCase: IGetStatementsUseCase): BasePresenter<StatementsContract.View>(), StatementsContract.Presenter{

    private var statements: StatementsResponse? = null
    private lateinit var mStatementList: Array<StatementsData>

    override fun getStatementsList(){
        getStatementsUseCase.execute().enqueue(object : Callback<StatementsResponse>{
            override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<StatementsResponse>, response: Response<StatementsResponse>) {
                statements = response.body()
                mStatementList = statements!!.statementList
                mView?.getList(mStatementList)
            }

        })
    }

}