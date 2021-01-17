package com.jeanjnap.bankapp.ui.login

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class LoginFormTest {

    private lateinit var form: LoginForm

    @Before
    fun setup() {
        form = LoginForm()
    }

    @Test
    fun setUser_withInvalidValue_shouldReturnsInvalidValidation() {
        form.user = anyString()
        assertFalse(form.isValidUsername())
    }

    @Test
    fun setUser_withValidValue_shouldReturnsValidValidation() {
        form.user = "user123@test.com"
        assertTrue(form.isValidUsername())
    }

    @Test
    fun setPassword_withInvalidValue_shouldReturnsInvalidValidation() {
        form.pass = anyString()
        assertFalse(form.isValidPassword())
    }

    @Test
    fun setPassword_withValidValue_shouldReturnsValidValidation() {
        form.pass = "aA!"
        assertTrue(form.isValidPassword())
    }

    @Test
    fun isFormValid_withValidUserAndInvalidPassword_shouldReturnsFalse() {
        form.user = "user123@test.com"
        form.pass = anyString()

        assertFalse(form.isFormValid())
    }

    @Test
    fun isFormValid_withInvalidUserAndValidPassword_shouldReturnsFalse() {
        form.user = anyString()
        form.pass = "aA!"

        assertFalse(form.isFormValid())
    }

    @Test
    fun isFormValid_withValidUserAndValidPassword_shouldReturnsTrue() {
        form.user = "user123@test.com"
        form.pass = "aA!"

        assertTrue(form.isFormValid())
    }
}