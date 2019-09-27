package com.gustavo.bankandroid.features.login.usecase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatePasswordImplTest {

    @Test
    fun `match password`() {
        assertTrue(ValidatePasswordImpl().invoke("aA2@"))
    }

    @Test
    fun `do not match password`(){
        assertFalse(ValidatePasswordImpl().invoke("aA2"))
        assertFalse(ValidatePasswordImpl().invoke("aA@"))
        assertFalse(ValidatePasswordImpl().invoke("a2@"))
        assertFalse(ValidatePasswordImpl().invoke("aaa"))
    }
}