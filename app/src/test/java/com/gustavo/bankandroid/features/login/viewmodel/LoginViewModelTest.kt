package com.gustavo.bankandroid.features.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.entity.UserLoginResponse
import com.gustavo.bankandroid.features.login.usecase.LoginUseCases
import com.gustavo.bankandroid.mock.MockData
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val authenticateUserUseCase: LoginUseCases.AuthenticateUserUseCase = mock()
    private val storeUserInfoUseCase: LoginUseCases.StoreUserInfoUseCase = mock()

    private lateinit var viewModel: LoginViewModel

    private lateinit var userInfo: UserInfo

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Before
    fun setupMocks(){
        viewModel = LoginViewModel(authenticateUserUseCase, storeUserInfoUseCase)
        userInfo = MockData.mockUserInfo()

    }

    @Test
    fun `login successful`() {
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(UserLoginResponse.Success(userInfo)))

        viewModel.login("test@test.com", "Test@1")

        val loginSuccess = viewModel.loginSuccessLiveData.value as Boolean
        assertTrue(loginSuccess)
    }

    @Test
    fun `login error`() {
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(UserLoginResponse.Error))

        viewModel.login("test@test.com", "Test@1")

        val loginSuccess = viewModel.loginSuccessLiveData.value as Boolean
        assertFalse(loginSuccess)
    }

    @Test
    fun `do not match password`(){
        viewModel.login("test@test.com", "aaaa")

        verify(authenticateUserUseCase, never()).execute(any(), any())
        val value = viewModel.validPasswordLiveData.value as Boolean
        assertFalse(value)
    }

    @Test
    fun `match password`(){
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(UserLoginResponse.Success(userInfo)))
        viewModel.login("test@test.com", "T@1")

        verify(authenticateUserUseCase).execute(any(), any())
        val value = viewModel.validPasswordLiveData.value as Boolean
        assertTrue(value)
    }

    @Test
    fun `accepts if username is email`(){
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(UserLoginResponse.Success(userInfo)))
        viewModel.login("test@test.com", "T@1")

        verify(authenticateUserUseCase).execute(any(), any())
        val value = viewModel.validUsernameLiveData.value as Boolean
        assertTrue(value)
    }

    @Test
    fun `accepts if username is cpf`(){
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(UserLoginResponse.Success(userInfo)))
        viewModel.login("000.000.000-00", "T@1")

        verify(authenticateUserUseCase).execute(any(), any())
        val value = viewModel.validUsernameLiveData.value as Boolean
        assertTrue(value)
    }

    @Test
    fun `refuse if username is net e-mail or cpf`(){
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(UserLoginResponse.Success(userInfo)))
        viewModel.login("00kdkd_ee", "T@1")

        verify(authenticateUserUseCase, never()).execute(any(), any())
        val value = viewModel.validUsernameLiveData.value as Boolean
        assertFalse(value)
    }
}