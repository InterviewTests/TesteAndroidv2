package com.jfgjunior.bankapp.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class UserCredentialsTest {

    @Test
    fun `verify user validation with email`() {
        val credentials = UserCredentials()

        var expected = false
        var result = credentials.isUserValid()

        assertEquals(expected, result)

        credentials.user = "test_user@gmail.com"

        expected = true
        result = credentials.isUserValid()

        assertEquals(expected, result)
    }

    @Test
    fun `verify user validation with CPF`() {
        val credentials = UserCredentials()

        var expected = false
        var result = credentials.isUserValid()

        assertEquals(expected, result)

        credentials.user = "11111111111"

        expected = true
        result = credentials.isUserValid()

        assertEquals(expected, result)

        credentials.user = "111.111.111-11"

        expected = true
        result = credentials.isUserValid()

        assertEquals(expected, result)
    }

    @Test
    fun `verify password validation`() {
        val credentials = UserCredentials()

        var expected = false
        var result = credentials.isPasswordValid()

        assertEquals(expected, result)

        credentials.password = "Test@1"

        expected = true
        result = credentials.isPasswordValid()

        assertEquals(expected, result)
    }

    @Test
    fun `verify if user is empty`() {
        val credentials = UserCredentials()

        var expected = true
        var result = credentials.isEmpty()

        assertEquals(expected, result)

        credentials.password = "Test@1"

        expected = true
        result = credentials.isEmpty()

        assertEquals(expected, result)

        credentials.password = ""
        credentials.user = "test_user@gmail.com"

        expected = true
        result = credentials.isEmpty()

        assertEquals(expected, result)

        credentials.password = "Test@1"
        credentials.user = "test_user@gmail.com"

        expected = false
        result = credentials.isEmpty()

        assertEquals(expected, result)
    }
}