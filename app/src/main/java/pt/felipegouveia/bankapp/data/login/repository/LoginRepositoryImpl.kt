package pt.felipegouveia.bankapp.data.login.repository

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.login.api.LoginService
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.data.login.model.LoginResponse
import pt.felipegouveia.bankapp.domain.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
): LoginRepository {

    override fun login(loginBody: LoginBody): Single<LoginResponse> {
        return loginService.login(loginBody)
    }

}