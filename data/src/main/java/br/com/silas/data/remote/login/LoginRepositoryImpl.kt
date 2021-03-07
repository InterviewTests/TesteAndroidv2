package br.com.silas.data.remote.login

import br.com.silas.data.remote.api.ServiceTesteAndroidV2
import br.com.silas.domain.preferences.PreferencesRepository
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.user.LoginRepository
import br.com.silas.domain.user.User
import io.reactivex.rxjava3.core.Single

class LoginRepositoryImpl(
    private val testeAndroidV2Service: ServiceTesteAndroidV2,
    private val userMapper: UserMapper,
) : LoginRepository {
    override fun fetchUser(login: String, password: String): Single<Pair<User, ErrorResponse>> {
        return testeAndroidV2Service.fetchUser(login, password)
            .map { userMapper.mapperUserAccountResponseToUser(it) }
    }
}