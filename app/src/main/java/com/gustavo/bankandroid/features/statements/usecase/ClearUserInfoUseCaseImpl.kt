package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.domain.contracts.StatementUseCases

class ClearUserInfoUseCaseImpl(
    private val userRepository: RepositoriesContract.UserRepository
) : StatementUseCases.ClearUserInfoUseCase {
    override fun execute() {
        userRepository.clearUsers()
    }
}