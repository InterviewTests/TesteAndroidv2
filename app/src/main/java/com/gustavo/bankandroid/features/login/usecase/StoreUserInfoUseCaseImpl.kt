package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.features.login.repository.UserRepository

class StoreUserInfoUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCases.StoreUserInfoUseCase {
    override fun execute(userInfo: UserInfo) {
        userRepository.saveUser(userInfo)
    }
}