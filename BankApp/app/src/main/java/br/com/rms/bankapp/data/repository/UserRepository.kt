package br.com.rms.bankapp.data.repository

import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.dao.UserDao
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import javax.inject.Singleton

@Singleton
class UserRepository(
    private val userDao: UserDao,
    private val accountDao: AccountDao,
    private val apiService: BankAppApiService
) {


}