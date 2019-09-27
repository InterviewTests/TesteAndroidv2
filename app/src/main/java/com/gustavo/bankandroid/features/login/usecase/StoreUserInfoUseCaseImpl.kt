package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.domain.contracts.LoginUseCases
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.entity.UserInfo

class StoreUserInfoUseCaseImpl(
    private val userRepository: RepositoriesContract.UserRepository
) : LoginUseCases.StoreUserInfoUseCase {
    override fun execute(userInfo: UserInfo) {
        userRepository.saveUser(userInfo)
    }
}