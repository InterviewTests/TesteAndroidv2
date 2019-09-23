package com.gustavo.bankandroid.features.login.repository

import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.data.mapper.UserInfoMapper
import io.reactivex.Single

class UserRepositoryImpl(
    private val userDatabase: UserDatabase,
    private val serverIterator: ServerIterator,
    private val mapper: UserInfoMapper = UserInfoMapper()
) : UserRepository {
    override fun saveUser(userInfo: UserInfo) {
        userDatabase.userDao.insertUserInfo(
            mapper.toDto(userInfo)
        )
    }

    override fun getSavedUser(): Single<UserInfo> {
        return userDatabase.userDao.getUserInfo()
            .map { mapper.toUserInfo(it) }
    }

    override fun authenticateUser(username: String, password: String): Single<LoginResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}