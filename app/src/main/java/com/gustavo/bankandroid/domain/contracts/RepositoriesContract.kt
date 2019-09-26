package com.gustavo.bankandroid.domain.contracts

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import com.gustavo.bankandroid.entity.UserStatementItem
import io.reactivex.Single

interface RepositoriesContract {
    interface UserRepository {
        fun authenticateUser(username: String, password: String): Single<UserLoginResponse>
        fun saveUser(userInfo: UserInfo)
        fun getSavedUser(): Single<UserInfo>
        fun clearUsers()
    }
    interface DataRepository {
        fun getUserStatementList(id:Int): Single<List<UserStatementItem>>
    }
}