package com.farage.testeandroidv2.domain.usecase

import com.farage.testeandroidv2.domain.UserRepository
import com.farage.testeandroidv2.domain.model.UserAccount
import com.farage.testeandroidv2.util.ResultState
import com.farage.testeandroidv2.util.isCPFValid
import com.farage.testeandroidv2.util.isEmailValid
import com.farage.testeandroidv2.util.isValidPassword

class UserLoginUseCase(private var userRepository: UserRepository) {
    suspend fun execute(user: String, password: String): ResultState<UserAccount> {
        return when {
            !user.isEmailValid() && !user.isCPFValid() -> ResultState.error("Email ou CPF invalido")
            !password.isValidPassword() -> ResultState.error("Senha invalida")
            else -> userRepository.userLogin(user, password)
        }
    }

}