package com.example.testeandroidsantander.controller

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginControllerTest {

    private lateinit var loginController: LoginController

    @Before
    fun setup() {
        val pater = mockk<Pattern>()
        loginController = LoginController(pater)
    }

    @Test
    fun validEmail_IsEmail() {
        val email = "hello@gmail.com"
        val pater = mockk<Pattern>()

        val matcher = mockk<Matcher>()
        every {  matcher.matches()} returns true

        every { pater.matcher(email)} returns matcher
        loginController = LoginController(pater)

        assertTrue(loginController.validEmail(email))
    }

    @Test
    fun validEmail_IsNotEmail() {
        val email = "hello@gmail.com"
        val pater = mockk<Pattern>()

        val matcher = mockk<Matcher>()
        every {  matcher.matches()} returns false

        every { pater.matcher(email)} returns matcher
        loginController = LoginController(pater)

        assertFalse(loginController.validEmail(email))
    }

    @Test
    fun validUpperCase_IsNotUpperCase() {
        val text = "santander"
        assertFalse(loginController.validUpperCase(text))
    }

    @Test
    fun validUpperCase_IsUpperCase() {
        val text = "Santander"
        assertTrue(loginController.validUpperCase(text))
    }

    @Test
    fun validSpecialCharacter_ThereIsNotSpecialCharacter() {
        val text = "santander"
        assertFalse(loginController.validSpecialCharacter(text))
    }

    @Test
    fun validSpecialCharacter_ThereIsSpecialCharacter() {
        val text = "s@ntander"
        assertTrue(loginController.validSpecialCharacter(text))
    }

    @Test
    fun validAlphaNumeric_IsAlphaNumeric() {
        val text = "banco"
        assertFalse(loginController.validAlphaNumeric(text))
    }

    @Test
    fun validAlphaNumeric_IsNotAlphaNumeric() {
        val text = "banco!!@"
        assertTrue(loginController.validAlphaNumeric(text))
    }

    @Test
    fun isCPF_IsNotCPF() {
        val cpf = "39144510879"
        assertFalse(loginController.isCPF(cpf))
    }

    @Test
    fun isCPF_IsCPF() {
        val cpf = "39144510845"
        assertTrue(loginController.isCPF(cpf))
    }

    @Test
    fun isCPF_IsNotValidCPF() {
        val cpf = "00000000000"
        assertFalse(loginController.isCPF(cpf))
    }
}