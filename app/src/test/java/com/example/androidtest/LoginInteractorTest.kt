package com.example.androidtest

import android.content.Context
import com.example.androidtest.api.ApiResponse
import com.example.androidtest.login.LoginActivityContract
import com.example.androidtest.login.LoginInteractor
import com.example.androidtest.login.LoginPresenter
import com.example.androidtest.login.LoginRepository
import com.example.androidtest.repository.User
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginInteractorTest {


    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var loginActivity: LoginActivityContract
    @Mock
    private lateinit var loginRepository: LoginRepository

    private lateinit var loginInteractor: LoginInteractor
    private lateinit var loginPresenter: LoginPresenter

    private val validUser = "genivaldo"
    private val validPassword = "securePassword#7291"
    private val invalidUser = "admin"
    private val invalidPassword = "1234"


    @Before
    fun beforeTests() {
        doReturn(context).whenever(loginActivity).getContext()

        loginPresenter = LoginPresenter(loginActivity)
        loginInteractor = LoginInteractor(loginPresenter, loginRepository)
    }


    @Test
    fun login_withBlankPass() {
        val user = validUser
        val pass = ""

        loginInteractor.requestLogin(user, pass)

        verify(loginActivity).showAlert(anyString())
        assertNull(loginRepository.loggedUser)
    }

    @Test
    fun login_withBlankUser() {
        val user = ""
        val pass = validPassword

        loginInteractor.requestLogin(user, pass)

        verify(loginActivity).showAlert(anyString())
        assertNull(loginRepository.loggedUser)
    }

    @Test
    fun login_withWrongData() {
        val user = invalidUser
        val pass = invalidPassword

        loginInteractor.requestLogin(user, pass)

        verify(loginRepository).loginCall(user, pass, )
        verify(loginActivity).showAlert(anyString())
        assertNull(loginRepository.loggedUser)
    }

    @Test
    fun login_withValidData() {
        val user = validUser
        val pass = validPassword

        doAnswer {
            loginRepository.checkinUser(User())
        }.whenever(loginRepository).loginCall(user, pass, any())

        loginInteractor.requestLogin(user, pass)

        verify(loginRepository, never()).loginCall(user, pass, any())
        verify(loginRepository).checkinUser(any())
        verify(loginActivity).navigateToHomeActivity()
        assertNotNull(loginRepository.loggedUser)
    }

}
