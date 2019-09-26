package com.gustavo.bankandroid.domain.contracts

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserStatementItem
import io.reactivex.Single

interface StatementUseCases {

    interface FetchStatementUseCase {
        fun execute(id: Int): Single<List<UserStatementItem>>
    }

    interface GetLoggedUserInfoUseCase {
        fun execute(): Single<UserInfo>
    }

    interface ClearUserInfoUseCase {
        fun execute()
    }
}