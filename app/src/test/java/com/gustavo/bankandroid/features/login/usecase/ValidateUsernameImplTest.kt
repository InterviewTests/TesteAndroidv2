package com.gustavo.bankandroid.features.login.usecase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateUsernameImplTest {

    @Test
    fun `match if is cpf`() {
        assertTrue(ValidateUsernameImpl().invoke("000.000.000-00"))
    }
    @Test
    fun `match if is e-mail`() {
        assertTrue(ValidateUsernameImpl().invoke("email@email.com"))
    }
    @Test
    fun `do not match`(){
        assertFalse(ValidateUsernameImpl().invoke("email.com"))
        assertFalse(ValidateUsernameImpl().invoke("000.000"))
        assertFalse(ValidateUsernameImpl().invoke("aaaaas"))
        assertFalse(ValidateUsernameImpl().invoke("332332323"))
    }
}