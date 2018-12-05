package com.br.natanfelipe.bankapp.login

import com.br.natanfelipe.bankapp.api.RestApi
import com.br.natanfelipe.bankapp.interactor.LoginInteractor
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginActivityTest {

    lateinit var loginActivityMock: LoginActivity
    lateinit var api: RestApi
    lateinit var loginInteractor: LoginInteractor


    @Before
    fun setUp() {
        loginInteractor = LoginInteractor()
        loginActivityMock = Mockito.mock(LoginActivity::class.java)
        api = RestApi()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun LoginActivity_ShoulNot_be_Null() {
        Assert.assertNotNull(loginActivityMock)
    }

    @Test
    fun validateIfUserIsValid(){
        loginInteractor = LoginInteractor()
        val isValid= loginInteractor.validateUsernameData("test@test.com")
        Assert.assertTrue(isValid)

    }

    @Test
    fun validateIfPassIsValid(){
        loginInteractor = LoginInteractor()
        val isValid= loginInteractor.validatePwdData("Test@1")
        Assert.assertTrue(isValid)
    }
}