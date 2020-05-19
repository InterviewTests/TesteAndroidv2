package br.com.bankapp.data.repository

import br.com.bankapp.data.source.LoginDataSource
import br.com.bankapp.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    override suspend fun attemptLogin(user: String, password: String) {
        loginDataSource.attemptLogin(user, password)
    }
}