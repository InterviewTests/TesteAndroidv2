package com.gustavo.bankandroid.features.login.repository

import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import io.reactivex.Single

interface UserRepository {
    fun authenticateUser(username: String, password: String): Single<LoginResponse>
    fun saveUser(userInfo: UserInfo)
    fun getSavedUser(): Single<UserInfo>
}