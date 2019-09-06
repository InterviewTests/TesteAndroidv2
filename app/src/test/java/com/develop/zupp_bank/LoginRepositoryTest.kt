package com.develop.zupp_bank

import com.develop.zupp_bank.presentation.login.LoginPresenter
import com.develop.zupp_bank.presentation.login.LoginView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class LoginRepositoryTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `login with correct login and password`() {

        //given
        val objectUnderTest = LoginPresenter(mock(LoginView::class.java))
        val correctLogin = "teste@teste.com"
        val correctPassword = "teste"

        //when
        val result: Boolean = objectUnderTest.processLogin(correctLogin, correctPassword).toString().toBoolean()

        //then
        assert(result)
    }

}