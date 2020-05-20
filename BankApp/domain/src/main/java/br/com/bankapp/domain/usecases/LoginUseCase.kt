package br.com.bankapp.domain.usecases

import br.com.bankapp.domain.repository.UserAccountRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: UserAccountRepository
) {
    suspend operator fun invoke(user: String, password: String) =
        loginRepository.attemptLogin(user, password)
}