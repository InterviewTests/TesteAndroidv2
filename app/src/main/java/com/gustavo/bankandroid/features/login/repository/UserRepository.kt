package com.gustavo.bankandroid.features.login.repository

import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import io.reactivex.Single

interface UserRepository {
    fun authenticateUser(username: String, password: String): Single<UserLoginResponse>
    fun saveUser(userInfo: UserInfo)
    fun getSavedUser(): Single<UserInfo>
    fun clearUsers()
}