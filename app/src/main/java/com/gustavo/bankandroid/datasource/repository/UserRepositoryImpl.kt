package com.gustavo.bankandroid.datasource.repository

import com.gustavo.bankandroid.datasource.api.ServerIterator
import com.gustavo.bankandroid.datasource.data.user.gson.UserLogin
import com.gustavo.bankandroid.datasource.data.user.mapper.LoginResponseMapper
import com.gustavo.bankandroid.datasource.data.user.mapper.UserInfoMapper
import com.gustavo.bankandroid.datasource.database.UserDatabase
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import io.reactivex.Single

class UserRepositoryImpl(
    private val userDatabase: UserDatabase,
    private val serverIterator: ServerIterator,
    private val userInfoMapper: UserInfoMapper = UserInfoMapper(),
    private val loginResponseMapper: LoginResponseMapper = LoginResponseMapper()
) : RepositoriesContract.UserRepository {

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

    override fun clearUsers() {
        userDatabase.userDao.deleteAll()
    }
}