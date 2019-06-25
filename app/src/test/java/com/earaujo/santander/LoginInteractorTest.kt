package com.earaujo.santander

import android.content.Context
import com.earaujo.santander.login.*
import com.earaujo.santander.repository.LoginRepository
import com.earaujo.santander.repository.LoginRepositoryCallback
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginRequest
import com.earaujo.santander.repository.models.LoginResponse
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class LoginInteractorTest {

    @Mock
    lateinit var loginPresenter: LoginPresenter

    @Mock
    lateinit var loginValidator: LoginValidator

    @Mock
    lateinit var context: Context

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when performLogin with valid username and password should call presentLoginResponse with a response from repository`() {
        //Prepare
        val loginResponse = Resource.success(LoginResponse(null,null))
        val loginRepository = LoginRepositoryImplTest(loginResponse)
        val loginInteractor = LoginInteractor(loginRepository, loginValidator)
        loginInteractor.loginPresenterInput = loginPresenter
        val loginRequest = LoginRequest("username", "password")

        whenever(context.getString(any())).thenReturn("test")
        whenever(loginValidator.isValidUsername(any())).thenReturn(true)
        whenever(loginValidator.isValidPassword(any())).thenReturn(true)

        //Action
        loginInteractor.performLogin(loginRequest)

        //Verification
        verify(loginPresenter, times(1)).presentLoginResponse(loginResponse)
    }

    @Test
    fun `when performLogin with invalid username should call presentLoginResponse with WRONG_USERNAME error`() {
        //Prepare
        val loginResponse = Resource.success(LoginResponse(null,null))
        val loginRepository = LoginRepositoryImplTest(loginResponse)
        val loginInteractor = LoginInteractor(loginRepository, loginValidator)
        loginInteractor.loginPresenterInput = loginPresenter
        val loginRequest = LoginRequest("username", "password")

        whenever(loginValidator.isValidUsername(any())).thenReturn(false)
        whenever(loginValidator.isValidPassword(any())).thenReturn(true)

        //Action
        loginInteractor.performLogin(loginRequest)

        //Verification
        verify(loginPresenter, times(1)).presentLoginResponse(argThat {
            this.message.equals(LoginInteractorErros.WRONG_USERNAME.errorNo)
        })
    }

    @Test
    fun `when performLogin with invalid password should call presentLoginResponse with WRONG_PASSWORD error`() {
        //Prepare
        val loginResponse = Resource.success(LoginResponse(null,null))
        val loginRepository = LoginRepositoryImplTest(loginResponse)
        val loginInteractor = LoginInteractor(loginRepository, loginValidator)
        loginInteractor.loginPresenterInput = loginPresenter
        val loginRequest = LoginRequest("username", "password")

        whenever(loginValidator.isValidUsername(any())).thenReturn(true)
        whenever(loginValidator.isValidPassword(any())).thenReturn(false)

        //Action
        loginInteractor.performLogin(loginRequest)

        //Verification
        verify(loginPresenter, times(1)).presentLoginResponse(argThat {
            this.message.equals(LoginInteractorErros.WRONG_PASSWORD.errorNo)
        })
    }

    inner class LoginRepositoryImplTest(var loginResponse: Resource<LoginResponse>) : LoginRepository {
        override fun fetchLogin(loginRequest: LoginRequest, loginRepositoryCallback: LoginRepositoryCallback) {
            loginRepositoryCallback.onData(loginResponse)
        }
    }
}