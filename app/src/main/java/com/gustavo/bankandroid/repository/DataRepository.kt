package com.gustavo.bankandroid.repository

import com.gustavo.bankandroid.data.gson.StatementResponse
import io.reactivex.Single

interface DataRepository {
    fun getUserStatementList(id:Int, password:String): Single<StatementResponse>
}