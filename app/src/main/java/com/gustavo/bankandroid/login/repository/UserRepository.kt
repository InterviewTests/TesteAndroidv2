package com.gustavo.bankandroid.login.repository

import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import io.reactivex.Single

interface UserRepository {
    fun authenticateUser(username: String, password: String): Single<LoginResponse>
    fun saveUser(userInfo: UserInfo)
    fun getSavedUser(): UserInfo
}