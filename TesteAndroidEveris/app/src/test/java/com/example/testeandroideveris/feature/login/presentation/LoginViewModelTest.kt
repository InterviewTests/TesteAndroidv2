package com.example.testeandroideveris.feature.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testeandroideveris.data.Resource
import com.example.testeandroideveris.feature.login.data.LoginDataState
import com.example.testeandroideveris.feature.login.data.LoginRequestData
import com.example.testeandroideveris.feature.login.data.UserAccount
import com.example.testeandroideveris.feature.login.data.UserAccountData
import com.example.testeandroideveris.feature.login.domain.usecases.LoginUseCase
import com.example.testeandroideveris.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var lastUserObserver: Observer<String?>

    @Mock
    private lateinit var dataStateObserver: Observer<LoginDataState>

    @Mock
    private lateinit var loginObserver: Observer<Resource<UserAccount>>

    val useCase: LoginUseCase =  Mockito.mock(LoginUseCase::class.java)

    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(useCase)
    }

    @Test
    fun `should get last logged user when initialize screen` () {
        runBlockingTest {
            doReturn("user@user.com")
                .`when`(useCase).getLastUser()

            loginViewModel.getLastUser().observeForever(lastUserObserver)
            verify(useCase).getLastUser()
            verify(lastUserObserver).onChanged("user@user.com")
            loginViewModel.getLastUser().removeObserver(lastUserObserver)
        }
    }

    @Test
    fun `should return success in validate when all data is correct` () {
        runBlockingTest {
            val user = "user@user.com"
            val password = "123Ddd@1"
            doReturn(LoginDataState.VALID_SUCCESS)
                .`when`(useCase).validate(LoginRequestData(user, password))

            loginViewModel.dataState.observeForever(dataStateObserver)
            loginViewModel.login(user, password)
            verify(useCase).validate(LoginRequestData(user, password))
            verify(dataStateObserver).onChanged(LoginDataState.VALID_SUCCESS)
            loginViewModel.dataState.removeObserver(dataStateObserver)
        }
    }

    @Test
    fun `should return invalid user in validate when user data is empty` () {
        runBlockingTest {
            val user = ""
            val password = "123Ddd@1"
            doReturn(LoginDataState.INVALID_USER)
                .`when`(useCase).validate(LoginRequestData(user, password))

            loginViewModel.dataState.observeForever(dataStateObserver)
            loginViewModel.login(user, password)
            verify(useCase).validate(LoginRequestData(user, password))
            verify(dataStateObserver).onChanged(LoginDataState.INVALID_USER)
            loginViewModel.dataState.removeObserver(dataStateObserver)
        }
    }

    @Test
    fun `should return invalid user in validate when user data is incomplete` () {
        runBlockingTest {
            val user = "user"
            val password = "123Ddd@1"
            doReturn(LoginDataState.INVALID_USER)
                .`when`(useCase).validate(LoginRequestData(user, password))

            loginViewModel.dataState.observeForever(dataStateObserver)
            loginViewModel.login(user, password)
            verify(useCase).validate(LoginRequestData(user, password))
            verify(dataStateObserver).onChanged(LoginDataState.INVALID_USER)
            loginViewModel.dataState.removeObserver(dataStateObserver)
        }
    }

    @Test
    fun `should return invalid password in validate when user data is empty` () {
        runBlockingTest {
            val user = "user@user.com"
            val password = ""
            doReturn(LoginDataState.INVALID_PASSWORD)
                .`when`(useCase).validate(LoginRequestData(user, password))

            loginViewModel.dataState.observeForever(dataStateObserver)
            loginViewModel.login(user, password)
            verify(useCase).validate(LoginRequestData(user, password))
            verify(dataStateObserver).onChanged(LoginDataState.INVALID_PASSWORD)
            loginViewModel.dataState.removeObserver(dataStateObserver)
        }
    }

    @Test
    fun `should return invalid password in validate when user data is incomplete` () {
        runBlockingTest {
            val user = "user@user.com"
            val password = "abc123"
            doReturn(LoginDataState.INVALID_PASSWORD)
                .`when`(useCase).validate(LoginRequestData(user, password))

            loginViewModel.dataState.observeForever(dataStateObserver)
            loginViewModel.login(user, password)
            verify(useCase).validate(LoginRequestData(user, password))
            verify(dataStateObserver).onChanged(LoginDataState.INVALID_PASSWORD)
            loginViewModel.dataState.removeObserver(dataStateObserver)
        }
    }

    @Test
    fun `should make login when all data is correct` () {
        runBlocking {
            val user = "user@user.com"
            val password = "abc123E@1"
            val response = UserAccount(1, "João Teste", "2010", "1234567", 3.0)
            doReturn(LoginDataState.VALID_SUCCESS)
                .`when`(useCase).validate(LoginRequestData(user, password))
            doReturn(flowOf(response))
                .`when`(useCase).login(LoginRequestData(user, password))

            loginViewModel.login.observeForever(loginObserver)
            loginViewModel.login(user, password)
            verify(useCase).login(LoginRequestData(user, password))
            verify(loginObserver).onChanged(Resource.success(response))
            loginViewModel.login.removeObserver(loginObserver)
        }
    }
}