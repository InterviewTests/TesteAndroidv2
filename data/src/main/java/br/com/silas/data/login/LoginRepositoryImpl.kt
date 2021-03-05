package br.com.silas.data.login

import br.com.silas.domain.user.LoginRepository
import br.com.silas.domain.user.User
import io.reactivex.rxjava3.core.Single

class LoginRepositoryImpl() : LoginRepository {
    override fun fetchUser(login: String, password: String): Single<User> {
        TODO("Not yet implemented")
    }
}