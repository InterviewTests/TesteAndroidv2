package pt.felipegouveia.bankapp.domain.interactors

import pt.felipegouveia.bankapp.domain.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
}