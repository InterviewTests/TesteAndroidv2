package com.gustavo.bankandroid.usecase

import com.gustavo.bankandroid.entity.UserStatementItem
import io.reactivex.Single

interface FetchStatementUseCase {
    fun execute(id:Int, password:String): Single<List<UserStatementItem>>
}