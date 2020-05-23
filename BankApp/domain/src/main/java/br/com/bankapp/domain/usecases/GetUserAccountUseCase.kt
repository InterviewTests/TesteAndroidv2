package br.com.bankapp.domain.usecases

import br.com.bankapp.domain.repository.UserAccountRepository
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(
    private val userAccountRepository: UserAccountRepository
) {
    operator fun invoke(userId: Int) =
        userAccountRepository.getUserAccount(userId)
}