package com.develop.tcs_bank

import com.develop.tcs_bank.presentation.login.LoginPresenter
import com.develop.tcs_bank.presentation.login.LoginView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class LoginRepositoryTest {

    lateinit var loginPresenter: LoginPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginPresenter = LoginPresenter(mock(LoginView::class.java))
    }

    @Test
    fun `login with correct login and password`() {

        //given
        //val objectUnderTest = LoginPresenter(mock(LoginView::class.java))

        val correctLogin = "teste@teste.com"
        val correctPassword = "teste"

        //when
        val result: Boolean = loginPresenter.processLogin(correctLogin, correctPassword).toString().toBoolean()

        //then
        assert(result)
    }

}