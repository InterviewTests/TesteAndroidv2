package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.domain.contracts.StatementUseCases
import com.gustavo.bankandroid.entity.UserInfo
import io.reactivex.Single

class GetLoggedUserInfoUseCaseImpl(
    private val userRepository: RepositoriesContract.UserRepository
): StatementUseCases.GetLoggedUserInfoUseCase {
    override fun execute(): Single<UserInfo> {
        return userRepository.getSavedUser()
    }
}