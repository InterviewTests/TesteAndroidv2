package com.gustavo.bankandroid.features.statements.repository

import com.gustavo.bankandroid.entity.UserStatementItem
import io.reactivex.Single

interface DataRepository {
    fun getUserStatementList(id:Int): Single<List<UserStatementItem>>
}