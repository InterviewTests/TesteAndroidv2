package pt.felipegouveia.bankapp.data.login.repository

import io.reactivex.Single
import pt.felipegouveia.bankapp.data.login.api.LoginService
import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.data.login.model.LoginMapper
import pt.felipegouveia.bankapp.domain.LoginRepository
import pt.felipegouveia.bankapp.domain.model.Login
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val loginMapper: LoginMapper
): LoginRepository {

    override fun login(loginBody: LoginBody): Single<Login> {
        return loginService.login(loginBody).map {
            loginMapper.mapLoginDataEntityToDomainEntity(it)
        }
    }

}