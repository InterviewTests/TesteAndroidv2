package com.example.androidtest

import android.content.Context
import com.example.androidtest.presentation.login.LoginActivityContract
import com.example.androidtest.presentation.login.LoginInteractor
import com.example.androidtest.presentation.login.LoginPresenter
import com.example.androidtest.repository.Repository
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginInteractorTest {


    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var loginActivity: LoginActivityContract

    private lateinit var loginInteractor: LoginInteractor
    private lateinit var loginPresenter: LoginPresenter

    private val validUser = "test_user"
    private val validPassword = "Test@1"
    private val invalidUser = "genivaldo"
    private val invalidPassword = "1234"


    @Before
    fun beforeTests() {
//        doReturn(context).`when`(loginActivity).getContext()
        Repository.logoff()
        loginPresenter = LoginPresenter(loginActivity)
        loginInteractor = LoginInteractor(loginPresenter)
    }


    @Test
    fun login_withBlankPass() {
        val user = validUser
        val pass = ""

        loginInteractor.requestLogin(user, pass)

        assertNull(Repository.logged)
    }

    @Test
    fun login_withBlankUser() {
        val user = ""
        val pass = validPassword

        loginInteractor.requestLogin(user, pass)

        assertNull(Repository.logged)
    }

    @Test
    fun login_withWrongData() {
        val user = invalidUser
        val pass = invalidPassword

        loginInteractor.requestLogin(user, pass)

        assertNull(Repository.logged)
    }

    @Test
    fun login_withValidData() {
        val user = validUser
        val pass = validPassword

        loginInteractor.requestLogin(user, pass)

        assertNotNull(Repository.logged)
    }

}
