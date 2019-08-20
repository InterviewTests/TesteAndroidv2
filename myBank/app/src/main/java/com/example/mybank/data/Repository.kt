package com.example.mybank.data

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.mybank.data.local.UserDatabase
import com.example.mybank.data.local.entity.User
import com.example.mybank.data.remote.MyBankApi
import com.example.mybank.data.remote.model.RecordTransactionResponse
import com.example.mybank.data.remote.model.RecordUserResponse
import com.example.mybank.data.remote.model.RequestLogin
import com.example.mybank.utils.Exceptions.LoginException
import com.example.mybank.utils.Exceptions.TransactionException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class Repository @Inject
constructor(private val mUserDatabase: UserDatabase, private val mMyBankApi: MyBankApi) {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun login(username: String, password: String) : RecordUserResponse? {
        deleteUser()
        val request = RequestLogin(username, password)
        return withContext(ioDispatcher) {
            try {
                val responseLogin = mMyBankApi.login(request).execute().body()!!

                if (responseLogin.error?.code != null) {
                    throw LoginException()
                }

                insertUser(responseLogin)
                return@withContext responseLogin
            } catch (e: Throwable) {
                throw LoginException()
            }
        }
    }

    suspend fun insertUser(responseUser: RecordUserResponse) {
        val userFromNetwork = responseUser.userAccount!!
        val user = User(
            userFromNetwork.userId,
            userFromNetwork.name,
            userFromNetwork.agency,
            userFromNetwork.bankAccount,
            userFromNetwork.balance
        )

        mUserDatabase.withTransaction {
            mUserDatabase.usuarioDao().insert(user)
        }
    }

    fun getUser() : LiveData<User> {
        return  mUserDatabase.usuarioDao().get()
    }

    suspend fun deleteUser() {
        mUserDatabase.withTransaction {
            mUserDatabase.usuarioDao().clear()
        }
    }

    suspend fun getUserTRansactions(userId: Int) : RecordTransactionResponse? {
        return withContext(ioDispatcher) {
            try {
                val responseUserTransaction = mMyBankApi.getUserTransactions(userId).execute().body()!!

                if (responseUserTransaction.error?.code != null) {
                    throw LoginException()
                }

                return@withContext responseUserTransaction
            } catch(e: Throwable) {
                throw TransactionException()
            }
        }
    }
}