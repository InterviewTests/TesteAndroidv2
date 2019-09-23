package com.gustavo.bankandroid.login.usecase

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.login.repository.UserRepository

class StoreUserInfoUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCases.StoreUserInfoUseCase {
    override fun execute(userInfo: UserInfo) {
        userRepository.saveUser(userInfo)
    }
}