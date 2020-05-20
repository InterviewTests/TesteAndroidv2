package br.com.bankapp.data.repository

import androidx.lifecycle.LiveData
import br.com.bankapp.data.source.LoginDataSource
import br.com.bankapp.data.source.UserAccountDataSource
import br.com.bankapp.domain.models.UserAccount
import br.com.bankapp.domain.repository.UserAccountRepository
import javax.inject.Inject

class UserAccountRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val userAccountDataSource: UserAccountDataSource
) : UserAccountRepository {

    override suspend fun attemptLogin(user: String, password: String) {
        loginDataSource.attemptLogin(user, password)
    }

    override fun getUserAccount(userId: Int): LiveData<UserAccount> {
        return userAccountDataSource.getUserAccount(userId)
    }
}