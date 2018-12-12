package br.com.rphmelo.bankapp.common.extensions

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StringExtTest {

    @Test
    fun should_assert_valid_email() {
        val value = "test@test.com"
        Assert.assertEquals(value.isEmail(), true)
    }

    @Test
    fun should_assert_invalid_email() {
        val value = "test.com"
        Assert.assertEquals(value.isEmail(), false)
    }

    @Test
    fun should_assert_valid_password() {
        val value = "ABC123$"
        Assert.assertEquals(value.isValidPassword(), true)
    }

    @Test
    fun should_assert_invalid_password() {
        val value = "ABC123"
        Assert.assertEquals(value.isValidPassword(), false)
    }

    @Test
    fun should_assert_valid_cpf() {
        val value = "12345678909"
        Assert.assertEquals(value.isValidCPF(), true)
    }

    @Test
    fun should_assert_invalid_cpf() {
        val value = "123456789"
        Assert.assertEquals(value.isValidCPF(), false)
    }
}