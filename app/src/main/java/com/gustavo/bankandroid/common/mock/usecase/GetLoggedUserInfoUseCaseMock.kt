package com.gustavo.bankandroid.common.mock.usecase

import com.gustavo.bankandroid.common.mock.MockData
import com.gustavo.bankandroid.domain.contracts.StatementUseCases
import com.gustavo.bankandroid.entity.UserInfo
import io.reactivex.Single

//mock used for ui testing purpose
class GetLoggedUserInfoUseCaseMock :
    StatementUseCases.GetLoggedUserInfoUseCase {

    private val userInfo
        get() = MockData.mockUserInfo()

    override fun execute(): Single<UserInfo> {
        return Single.just(userInfo)
    }

}