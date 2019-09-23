package com.gustavo.bankandroid.statements.usecase

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.login.repository.UserRepository
import io.reactivex.Single

class GetLoggedUserInfoUseCaseImpl(
    private val userRepository: UserRepository
):StatementUseCases.GetLoggedUserInfoUseCase {
    override fun execute(): Single<UserInfo> {
        return Single.just(userRepository.getSavedUser())
    }
}