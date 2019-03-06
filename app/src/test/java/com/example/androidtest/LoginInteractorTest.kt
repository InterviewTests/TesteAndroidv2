package com.example.androidtest

import android.content.Context
import com.example.androidtest.presentation.login.LoginActivityContract
import com.example.androidtest.presentation.login.LoginInteractor
import com.example.androidtest.presentation.login.LoginPresenterContract
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginInteractorTest {


    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var loginActivity: LoginActivityContract
    @Mock
    private lateinit var loginPresenter: LoginPresenterContract

    private lateinit var loginInteractor: LoginInteractor

    private val validUser = "test_user"
    private val validPassword = "Test@1"


    @Before
    fun beforeTests() {
        doReturn(context).`when`(loginActivity).getContext()
        loginInteractor = LoginInteractor(context, loginPresenter)
    }


    @Test
    fun login_withBlankPass() {
        val user = validUser
        val pass = ""

        loginInteractor.requestLogin(user, pass)

        verify(loginPresenter).invalidInputForm()
    }

    @Test
    fun login_withBlankUser() {
        val user = ""
        val pass = validPassword

        loginInteractor.requestLogin(user, pass)

        verify(loginPresenter).invalidInputForm()
    }


}
