package com.gustavo.bankandroid.features.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.entity.UserInfo
import com.gustavo.bankandroid.features.login.usecase.LoginUseCases
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
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
        userInfo = UserInfo(1, "username", "1234", "name", "account", 1000)

    }

    @Test
    fun `login successful`() {
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(LoginResponse.Success(userInfo)))

        viewModel.login("", "")

        val loginSuccess = viewModel.loginSuccessLiveData.value as Boolean
        assertTrue(loginSuccess)
    }

    @Test
    fun `login error`() {
        whenever(authenticateUserUseCase.execute(any(), any())).thenReturn(Single.just(LoginResponse.Error))

        viewModel.login("", "")

        val loginSuccess = viewModel.loginSuccessLiveData.value as Boolean
        assertFalse(loginSuccess)
    }
}