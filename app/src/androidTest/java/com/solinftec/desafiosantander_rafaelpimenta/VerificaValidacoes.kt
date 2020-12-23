package com.solinftec.desafiosantander_rafaelpimenta

import com.solinftec.desafiosantander_rafaelpimenta.util.LoginValidate
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class VerificaValidacoes {

    private val loginValidate = LoginValidate()


    @Test
    fun IsCPF() {
        val cpf = "46428511860"
        assertTrue(loginValidate.isCPF(cpf))
    }

    @Test
    fun IsNotCPF() {
        val cpf = "4642851186"
        assertFalse(loginValidate.isCPF(cpf))
    }

    @Test
    fun IsEmail() {
        val email = "rafael@gmail.com"
        assertTrue(  loginValidate.validEmail(email))
    }
    @Test
    fun IsNotEmail() {
        val email = "rafaelgmail.com"

        assertFalse(loginValidate.validEmail(email))
    }

    @Test
    fun IsNotUpperCase() {
        val user = "user"
        assertFalse(loginValidate.validUpperCase(user))
    }

    @Test
    fun validUpperCase_IsUpperCase() {
        val user = "User"
        assertTrue(loginValidate.validUpperCase(user))
    }

    @Test
    fun IsNotSpecialCharacter() {
        val user = "user"
        assertFalse(loginValidate.validSpecialCharacter(user))
    }

    @Test
    fun IsSpecialCharacter() {
        val user = "user_@"
        assertTrue(loginValidate.validSpecialCharacter(user))
    }

    @Test
    fun IsAlphaNumeric() {
        val user = "user545!@"
        assertFalse(loginValidate.validarNumber(user))
    }

    @Test
    fun IsNotAlphaNumeric() {
        val user = "user"
        assertTrue(loginValidate.validarNumber(user))
    }

}