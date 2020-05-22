package br.com.bankapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.bankapp.data.api.BankAppApiService
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.mappers.toDomain
import br.com.bankapp.data.mappers.toEntity
import br.com.bankapp.data.utils.SharedPrefsHelper
import br.com.bankapp.domain.models.UserAccount
import javax.inject.Inject

class UserAccountDataSource @Inject constructor(
    private val userAccountDao: UserAccountDao,
    private val apiService: BankAppApiService,
    private val sharedPrefsHelper: SharedPrefsHelper
) {

    suspend fun attemptLogin(user: String, password: String) {
        val loginResponse = apiService.login("test_user", "Test@1")
        userAccountDao.clearAndInsert(loginResponse.userAccount!!.toEntity())
        sharedPrefsHelper.put(SharedPrefsHelper.PREF_USER, user)
        sharedPrefsHelper.put(SharedPrefsHelper.PREF_USER_ID, loginResponse.userAccount?.userId!!)
    }

    fun getUserAccount(userId: Int): LiveData<UserAccount> {
        return Transformations.map(userAccountDao.getUserByIdDistinct(userId)) {
            it.toDomain()
        }
    }
}