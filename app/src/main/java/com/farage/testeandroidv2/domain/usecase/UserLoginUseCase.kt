package com.farage.testeandroidv2.domain.usecase

import com.farage.testeandroidv2.domain.UserRepository
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.util.*

class UserLoginUseCase(private var userRepository: UserRepository) {

    suspend fun executeLogin(user: String, password: String): ResultState<UserAccount> {
        return when {
            !user.isEmailValid() && !user.isCPFValid() -> ResultState.error(Constants.WRONG_EMAIL_CPF)
            !password.isValidPassword() -> ResultState.error(Constants.WRONG_PASSWORD)
            else -> userRepository.userLogin(user, password)
        }
    }

}