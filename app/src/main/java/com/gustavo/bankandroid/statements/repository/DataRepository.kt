package com.gustavo.bankandroid.statements.repository

import com.gustavo.bankandroid.statements.data.gson.StatementResponse
import io.reactivex.Single

interface DataRepository {
    fun getUserStatementList(id:Int, password:String): Single<StatementResponse>
}