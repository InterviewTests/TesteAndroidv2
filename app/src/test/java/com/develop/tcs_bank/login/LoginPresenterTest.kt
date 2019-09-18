package com.develop.tcs_bank.login

import com.develop.tcs_bank.presentation.login.LoginContract
import com.develop.tcs_bank.presentation.login.LoginPresenter
import com.develop.tcs_bank.presentation.login.LoginView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoginPresenterTest {


    private lateinit var loginPresenter: LoginContract.Presenter

    @Mock
    private lateinit var loginView: LoginView

    @Before
    fun setUp(){

        MockitoAnnotations.initMocks(this)
        loginPresenter = LoginPresenter(loginView)

    }

    @Test
    fun processLogin_NullString(){

        loginPresenter.processLogin("","")
        verify(loginView, never())

    }
}