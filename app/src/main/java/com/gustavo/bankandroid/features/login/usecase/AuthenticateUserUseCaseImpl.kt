package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.features.login.repository.UserRepository
import io.reactivex.Single

class AuthenticateUserUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCases.AuthenticateUserUseCase {
    override fun execute(username: String, password: String): Single<LoginResponse> {
        return userRepository.authenticateUser(username, password)
    }
}