package com.example.androidtest

import android.content.Context
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginActivityTest {

    @Mock
    private lateinit var context: Context

    private lateinit var loginInteractor: LoginInteractor
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var loginRepository: LoginRepository

    private val validUser = "genivaldo"
    private val validPassword = "securePassword#7291"
    private val invalidUser = "admin"
    private val invalidPassword = "1234"


    @Before
    fun beforeTests() {
        loginPresenter = LoginPresenter(context)
        loginRepository = LoginRepository()
        loginInteractor = LoginInteractor(loginPresenter, loginRepository)
    }

    @Test
    fun login_withBlankUser() {
        loginInteractor.requestLogin(validUser, "")
        verify(loginRepository, never()).loginCall()
        verify(loginPresenter).showInvalidInputAlert()
        assertNull(loginRepository.loggedUser)
    }

    @Test
    fun login_withBlankUserPass() {
        loginInteractor.requestLogin("", validPassword)
        verify(loginRepository, never()).loginCall()
        verify(loginPresenter).showInvalidInputAlert()
        assertNull(loginRepository.loggedUser)
    }

    @Test
    fun login_withWrongData() {

        loginInteractor.requestLogin(invalidUser, invalidPassword)

        verify(loginRepository).loginCall()
        verify(loginPresenter).showInvalidCredentialsAlert()
        assertNull(loginRepository.loggedUser)
    }

    @Test
    fun login_withValidData() {

        doAnswer{
            loginRepository.checkinUser(User())
        }.`when`(loginRepository).loginCall()

        loginInteractor.requestLogin(validUser, validPassword)

        verify(loginRepository).loginCall()
        verify(loginRepository).checkinUser(any(User::class.java))
        verify(loginPresenter).navigateToHomeActivity()
        assertNotNull(loginRepository.loggedUser)
    }

}
