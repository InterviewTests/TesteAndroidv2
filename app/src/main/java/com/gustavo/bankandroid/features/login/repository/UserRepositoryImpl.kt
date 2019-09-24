package com.gustavo.bankandroid.features.login.repository

import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.data.gson.UserLogin
import com.gustavo.bankandroid.features.login.data.mapper.LoginResponseMapper
import com.gustavo.bankandroid.features.login.data.mapper.UserInfoMapper
import io.reactivex.Single

class UserRepositoryImpl(
    private val userDatabase: UserDatabase,
    private val serverIterator: ServerIterator,
    private val userInfoMapper: UserInfoMapper = UserInfoMapper(),
    private val loginResponseMapper: LoginResponseMapper = LoginResponseMapper()
) : UserRepository {
    override fun saveUser(userInfo: UserInfo) {
        userDatabase.userDao.insertUserInfo(
            userInfoMapper.toDto(userInfo)
        )
    }

    override fun getSavedUser(): Single<UserInfo> {
        return userDatabase.userDao.getUserInfo()
            .map { userInfoMapper.toUserInfo(it) }
    }

    override fun authenticateUser(username: String, password: String): Single<UserLoginResponse> {
        return serverIterator.loginUser(UserLogin(username, password)).map {
            loginResponseMapper.fromServer(it)
        }
    }
}