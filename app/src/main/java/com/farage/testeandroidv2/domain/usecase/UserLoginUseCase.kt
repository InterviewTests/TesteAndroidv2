package com.farage.testeandroidv2.domain.usecase

import com.farage.testeandroidv2.domain.UserRepository
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.util.ResultState

class UserLoginUseCase(private var userRepository: UserRepository) {
    suspend fun execute(): ResultState<UserAccount> {
        val user = userRepository.userLogin("test", "test")

        return user

    }

}