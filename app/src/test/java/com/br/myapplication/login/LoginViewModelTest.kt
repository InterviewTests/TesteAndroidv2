package com.br.myapplication.login

import com.br.myapplication.helper.SharedPreferencesManager
import com.br.myapplication.usercase.LoginUserCase
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import org.junit.Test

import org.junit.Before

class LoginViewModelTest {

    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        val loginUsercaseMock = mock<LoginUserCase>()
        val sharedPreferencesManager = mock<SharedPreferencesManager>()
        loginViewModel = LoginViewModel(loginUsercaseMock, sharedPreferencesManager)
    }
    
    @Test
    fun isValidateUserField() {
        val cpf = "270.018.580-30"
        assertEquals(loginViewModel.isValidateUserField(cpf), true)
    }

    @Test
    fun isValidPasswordField() {
        val password = "Dev@dev1602"
        assertEquals(loginViewModel.isValidPasswordField(password), true)
    }

    @Test
    fun `checkValidEmail$app_debug`() {
        val email = "dev@gmail.com"
        assertEquals(loginViewModel.checkValidEmail(email), true)
    }

    @Test
    fun `checkCpf$app_debug`() {
        val cpf = "270.018.580-30"
        assertEquals(loginViewModel.checkCpf(cpf), true)
    }
}