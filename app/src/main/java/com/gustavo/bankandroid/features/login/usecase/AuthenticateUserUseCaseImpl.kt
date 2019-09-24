package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.entity.UserLoginResponse
import com.gustavo.bankandroid.features.login.repository.UserRepository
import io.reactivex.Single

class AuthenticateUserUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCases.AuthenticateUserUseCase {
    override fun execute(username: String, password: String): Single<UserLoginResponse> {
        return userRepository.authenticateUser(username, password)
    }
}