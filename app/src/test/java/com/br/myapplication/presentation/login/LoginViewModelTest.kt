package com.br.myapplication.presentation.login

import androidx.lifecycle.MutableLiveData
import com.br.myapplication.data.model.Error
import com.br.myapplication.data.model.ResponseLogin
import com.br.myapplication.data.model.UserAccount
import com.br.myapplication.data.service.ApiResult
import com.br.myapplication.domain.usecase.LoginUseCase
import com.br.myapplication.domain.usecase.UserUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class LoginViewModelTest {

    lateinit var loginViewModel: LoginViewModel
    lateinit var loginUseCaseMock: LoginUseCase
    lateinit var userUseCase: UserUseCase

    @Before
    fun setup() {
        loginUseCaseMock = mock()
        userUseCase = mock()
        loginViewModel = LoginViewModel(loginUseCaseMock, userUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loginInvokeTest() {
        val params = LoginUseCase.Params(anyString(), anyString())
        loginViewModel.doLogin(params.user, params.password)
        runBlockingTest {
            whenever(loginUseCaseMock.run(params)).thenReturn(
                ResponseLogin(
                    UserAccount("", "", "", "", 1.0),
                    Error(null, message = ""))
            )
            verify(loginUseCaseMock, times(1)).invoke(eq(params), argThat { true })
            verify(loginUseCaseMock, times((1))).run(params)
        }
    }
}