package com.gustavo.bankandroid.statements.usecase

import com.gustavo.bankandroid.entity.UserInfo
import io.reactivex.Single

class GetLoggedUserInfoUseCaseMock : StatementUseCases.GetLoggedUserInfoUseCase {

    private val userInfo
        get() = UserInfo(1, "username", "1234", "name", "account", 1000)

    override fun execute(): Single<UserInfo> {
        return Single.just(userInfo)
    }

}