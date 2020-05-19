package br.com.bankapp.domain.usecases

import br.com.bankapp.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(user: String, password: String) =
        loginRepository.attemptLogin(user, password)
}