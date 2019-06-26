package com.earaujo.santander.login

import com.earaujo.santander.login.LoginActivityInput
import com.earaujo.santander.login.LoginPresenter
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.LoginResponse
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.ref.WeakReference

class LoginPresenterTest {

    @Mock
    lateinit var loginActivityInput: LoginActivityInput

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when presentLoginResponse should call loginCallback with the same parameter received`() {
        //Prepare
        val loginPresenter = LoginPresenter()
        val loginResponse: Resource<LoginResponse> = Resource.success(null)
        loginPresenter.loginActivityInput = WeakReference(loginActivityInput)

        //Action
        loginPresenter.presentLoginResponse(loginResponse)

        //Verification
        verify(loginActivityInput, times(1)).loginCallback(loginResponse)
    }

    @Test
    fun `when presentSetUserName should call displayUserName with the same parameter received`() {
        //Prepare
        val loginPresenter = LoginPresenter()
        val userName = "username"
        loginPresenter.loginActivityInput = WeakReference(loginActivityInput)

        //Action
        loginPresenter.presentSetUserName(userName)

        //Verification
        verify(loginActivityInput, times(1)).displayUserName(userName)
    }
}