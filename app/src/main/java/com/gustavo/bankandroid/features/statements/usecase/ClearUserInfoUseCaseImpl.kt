package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.features.login.repository.UserRepository

class ClearUserInfoUseCaseImpl(
    private val userRepository: UserRepository
) : StatementUseCases.ClearUserInfoUseCase {
    override fun execute() {
        userRepository.clearUsers()
    }
}