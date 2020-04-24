package com.tata.bank

import com.tata.bank.login.CredentialsValidatorWorker
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CredentialsValidatorWorkerTest {

    @Test
    fun `When enter correct password, expect success`() {
        val result = CredentialsValidatorWorker.isPasswordValid("Test@1")
        assertThat(result, equalTo(true))
    }

    @Test
    fun `When enter correct password without number, expect false`() {
        val result = CredentialsValidatorWorker.isPasswordValid("Test@")
        assertThat(result, equalTo(false))
    }

    @Test
    fun `When enter correct password without special character, expect false`() {
        val result = CredentialsValidatorWorker.isPasswordValid("Test1")
        assertThat(result, equalTo(false))
    }

    @Test
    fun `When enter correct password without uppercase letter, expect false`() {
        val result = CredentialsValidatorWorker.isPasswordValid("test@1")
        assertThat(result, equalTo(false))
    }

    @Test
    fun `When enter valid cpf as user, expect success`() {
        val result = CredentialsValidatorWorker.isUserValid("123.456.789-10")
        assertThat(result, equalTo(true))
    }

    @Test
    fun `When enter invalid cpf as user, expect false`() {
        val result = CredentialsValidatorWorker.isUserValid("123.456.789-1")
        assertThat(result, equalTo(false))
    }

    @Test
    fun `When enter cpf in right format and invalid character as user, expect false`() {
        val result = CredentialsValidatorWorker.isUserValid("abc.456.789-1")
        assertThat(result, equalTo(false))
    }

    @Test
    fun `When enter valid email as user, expect success`() {
        val result = CredentialsValidatorWorker.isUserValid("meu_email@email.com")
        assertThat(result, equalTo(true))
    }

    @Test
    fun `When enter INvalid email as user, expect false`() {
        val result = CredentialsValidatorWorker.isUserValid("meu_email@email.")
        assertThat(result, equalTo(false))
    }
}