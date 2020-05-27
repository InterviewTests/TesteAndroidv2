package com.br.myapplication.login

import androidx.lifecycle.MutableLiveData
import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.service.ApiResult
import com.br.myapplication.usecase.LoginUseCase
import com.br.myapplication.usecase.UserUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
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


    @Test
    fun testando() {
        val params = LoginUseCase.Params("", "")
        loginViewModel.doLogin("", "")
        val liveData:  MutableLiveData<ApiResult<ResponseLogin>> = MutableLiveData()
        whenever(loginViewModel.liveDataResponse).thenReturn(liveData)
        doNothing().whenever(loginUseCaseMock).invoke(params, liveData)
        verify(loginUseCaseMock, times(1)).invoke(params, liveData)
    }
}