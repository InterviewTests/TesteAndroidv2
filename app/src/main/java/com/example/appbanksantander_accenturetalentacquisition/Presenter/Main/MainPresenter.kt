package com.example.appbanksantander_accenturetalentacquisition.Presenter.Main

import android.content.Context
import com.example.appbanksantander_accenturetalentacquisition.API.RequestApi
import com.example.appbanksantander_accenturetalentacquisition.API.ServiceApi
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementModel
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementResponse

class MainPresenter(statementView: MainContract.View?, context: Context): MainContract.UserLoads {
    val serviceApi: ServiceApi
    val myStatementView: MainContract.View?

    init {
        serviceApi = RequestApi(context)
        myStatementView = statementView
    }

    override fun loadStatement(userId: Int) {
        serviceApi.getStatement(userId, object: ServiceApi.ServiceApiCallbackStatement<StatementResponse>{
            override fun loaded(statement: List<StatementModel>) {
                myStatementView?.showStatement(statement)
            }
        })
    }
}