package br.com.bankapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.mappers.toDomain
import br.com.bankapp.domain.models.UserAccount
import javax.inject.Inject

class UserAccountDataSource @Inject constructor(
    private val userAccountDao: UserAccountDao
) {
    fun getUserAccount(userId: Int): LiveData<UserAccount> {
        return Transformations.map(userAccountDao.getUserByIdDistinct(userId)) {
            it.toDomain()
        }
    }
}