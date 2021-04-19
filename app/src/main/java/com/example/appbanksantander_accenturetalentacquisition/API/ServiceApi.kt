package com.example.appbanksantander_accenturetalentacquisition.API

import com.example.appbanksantander_accenturetalentacquisition.Model.LoginResponse
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementModel
import com.example.appbanksantander_accenturetalentacquisition.Model.StatementResponse
import com.example.appbanksantander_accenturetalentacquisition.Model.UserAccountModel

interface ServiceApi {
    interface ServiceApiCallbackUser<T>{
        fun loaded(userAccount: UserAccountModel)
    }
    interface ServiceApiCallbackStatement<T>{
        fun loaded(statement: List<StatementModel>)
    }
    fun getAccountInfo(callback: ServiceApiCallbackUser<UserAccountModel>)
    fun getStatement(idUser: Int?, callback: ServiceApiCallbackStatement<StatementResponse>)
}