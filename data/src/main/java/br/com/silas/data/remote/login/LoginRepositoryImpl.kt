package br.com.silas.data.remote.login

import br.com.silas.data.remote.api.TesteAndroidV2Service
import br.com.silas.domain.preferences.PreferencesRepository
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.user.LoginRepository
import br.com.silas.domain.user.User
import io.reactivex.rxjava3.core.Single

class LoginRepositoryImpl(
    private val testeAndroidV2Service: TesteAndroidV2Service,
    private val userMapper: UserMapper,
    private val preferencesRepository: PreferencesRepository
) : LoginRepository {
    override fun fetchUser(login: String, password: String): Single<Pair<User, ErrorResponse>> {
        return testeAndroidV2Service.fetchUser(login, password)
            .map { userMapper.mapperUserAccountResponseToUser(it) }
            .doAfterSuccess {
                preferencesRepository.save(it.first)
                    .subscribe()
                    .dispose()
            }
    }
}