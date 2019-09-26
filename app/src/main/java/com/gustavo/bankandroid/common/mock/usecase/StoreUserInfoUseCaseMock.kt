package com.gustavo.bankandroid.common.mock.usecase

import com.gustavo.bankandroid.domain.contracts.LoginUseCases
import com.gustavo.bankandroid.entity.UserInfo

//mock used for ui testing purpose
class StoreUserInfoUseCaseMock:
    LoginUseCases.StoreUserInfoUseCase {
    override fun execute(userInfo: UserInfo) {

    }
}