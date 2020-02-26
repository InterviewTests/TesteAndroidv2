package com.br.myapplication.helper

import org.junit.Test

import org.junit.Assert.*

class LoginHelperTest {

    @Test
    fun isValidateUserField() {
        val cpf = "270.018.580-30"
        assertEquals(LoginHelper.isValidateUserField(cpf), true)
    }

    @Test
    fun isValidPasswordField() {
        val password = "Dev@dev1602"
        assertEquals(LoginHelper.isValidPasswordField(password), true)
    }

    @Test
    fun `checkValidEmail$app_debug`() {
        val email = "dev@gmail.com"
        assertEquals(LoginHelper.checkValidEmail(email), true)
    }

    @Test
    fun `checkCpf$app_debug`() {
        val cpf = "270.018.580-30"
        assertEquals(LoginHelper.checkCpf(cpf), true)
    }
}