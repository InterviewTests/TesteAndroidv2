package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.mock.MockData
import io.reactivex.Single

class GetLoggedUserInfoUseCaseMock : StatementUseCases.GetLoggedUserInfoUseCase {

    private val userInfo
        get() = MockData.mockUserInfo()

    override fun execute(): Single<UserInfo> {
        return Single.just(userInfo)
    }

}