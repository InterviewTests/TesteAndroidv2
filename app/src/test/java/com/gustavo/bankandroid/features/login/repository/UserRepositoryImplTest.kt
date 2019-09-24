package com.gustavo.bankandroid.features.login.repository

import com.gustavo.bankandroid.api.ServerIterator
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import com.gustavo.bankandroid.features.login.data.UserDao
import com.gustavo.bankandroid.features.login.data.UserDatabase
import com.gustavo.bankandroid.features.login.data.mapper.LoginResponseMapper
import com.gustavo.bankandroid.features.login.data.mapper.UserInfoMapper
import com.gustavo.bankandroid.mock.MockData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private val userDatabase: UserDatabase = mock()
    private val userDao: UserDao = mock()
    private val serverIterator: ServerIterator = mock()
    private val userInfoMapper: UserInfoMapper = mock()
    private val loginResponseMapper: LoginResponseMapper = mock()

    private lateinit var repository: UserRepositoryImpl

    val userInfo = MockData.mockUserInfo()
    val userInfoDto = MockData.mockUserInfoDto()

    val username = "username"
    val password = "password"

    val serverResponse = MockData.mockServerLoginResponse()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        repository =
            UserRepositoryImpl(userDatabase, serverIterator, userInfoMapper, loginResponseMapper)
        whenever(userDatabase.userDao).thenReturn(userDao)
    }

    @Test
    fun saveUser() {
        repository.saveUser(userInfo)
        whenever(userInfoMapper.toDto(any())).thenReturn(mock())
        verify(userInfoMapper).toDto(userInfo)
        verify(userDatabase.userDao).insertUserInfo(userInfoMapper.toDto(any()))
    }

    @Test
    fun getSavedUser() {
        whenever(userDatabase.userDao.getUserInfo()).thenReturn(Single.just(userInfoDto))

        whenever(userInfoMapper.toUserInfo(any())).thenReturn(userInfo)

        val testSingleObserver = TestObserver<UserInfo>()
        val result = repository.getSavedUser()
        result.subscribe(testSingleObserver)

        verify(userInfoMapper).toUserInfo(userInfoDto)
        assertEquals(testSingleObserver.values()[0].userId, userInfoDto.userId)
    }

    @Test
    fun authenticateUser() {
        whenever(serverIterator.loginUser(any())).thenReturn(Single.just(serverResponse))
        whenever(loginResponseMapper.fromServer(any())).thenReturn(UserLoginResponse.Success(userInfo))

        val testSingleObserver = TestObserver<UserLoginResponse>()
        val result = repository.authenticateUser(username, password)
        result.subscribe(testSingleObserver)

        verify(loginResponseMapper).fromServer(serverResponse)

        assertTrue((testSingleObserver.values()[0] is UserLoginResponse.Success))
    }
}