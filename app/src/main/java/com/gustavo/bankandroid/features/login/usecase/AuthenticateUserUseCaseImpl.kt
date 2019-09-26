package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.domain.contracts.LoginUseCases
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.entity.UserLoginResponse
import io.reactivex.Single

class AuthenticateUserUseCaseImpl(
    private val userRepository: RepositoriesContract.UserRepository
) : LoginUseCases.AuthenticateUserUseCase {
    override fun execute(username: String, password: String): Single<UserLoginResponse> {
        return userRepository.authenticateUser(username, password)
    }
}