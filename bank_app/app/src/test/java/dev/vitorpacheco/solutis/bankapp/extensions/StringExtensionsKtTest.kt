package dev.vitorpacheco.solutis.bankapp.extensions

import org.junit.Assert.*
import org.junit.Test

class StringExtensionsKtTest {

    @Test
    fun `test isValidEmail with null object`() {
        val email = null

        assertFalse(email.isValidEmail())
    }

    @Test
    fun `test isValidEmail with empty object`() {
        val email = ""

        assertFalse(email.isValidEmail())
    }

    @Test
    fun `test isValidEmail with invalid values`() {
        assertFalse("a@a".isValidEmail())
        assertFalse("a@.com".isValidEmail())
        assertFalse(".coma@.com".isValidEmail())
    }

    @Test
    fun `test isValidEmail with valid values`() {
        assertTrue("aa@android.com.br".isValidEmail())
        assertTrue("a@a2.com".isValidEmail())
        assertTrue("test@example.com".isValidEmail())
    }

    @Test
    fun `test isValidPassword with null object`() {
        val password = null

        assertFalse(password.isValidPassword())
    }

    @Test
    fun `test isValidPassword with empty object`() {
        val password = ""

        assertFalse(password.isValidPassword())
    }

    @Test
    fun `test isValidPassword with invalid values`() {
        assertFalse("1nv4l1d".isValidPassword())
        assertFalse("#%#!sda".isValidPassword())
        assertFalse("345#$%".isValidPassword())
    }

    @Test
    fun `test isValidPassword with valid values`() {
        assertTrue("a1!".isValidPassword())
        assertTrue("asd456&*(".isValidPassword())
        assertTrue("v4!id".isValidPassword())
    }

    @Test
    fun `test isValidCpf with null object`() {
        val cpf = null

        assertFalse(cpf.isValidCpf())
    }

    @Test
    fun `test isValidCpf with empty object`() {
        val cpf = ""

        assertFalse(cpf.isValidCpf())
    }

    @Test
    fun `test isValidCpf with invalid values`() {
        assertFalse("00703368557".isValidCpf())
        assertFalse("31551610119".isValidCpf())
        assertFalse("49497256513".isValidCpf())
        assertFalse("497256513".isValidCpf())
        assertFalse("4asd97256513".isValidCpf())
        assertFalse("030.144.105-50".isValidCpf())
        assertFalse("050.148-40".isValidCpf())
    }

    @Test
    fun `test isValidCpf with valid values`() {
        assertTrue("23739669055".isValidCpf())
        assertTrue("95903708021".isValidCpf())
        assertTrue("18664720037".isValidCpf())
    }

    @Test
    fun `test isValidCpf with formatted valid values`() {
        assertFalse("226.843.510-58".isValidCpf())
        assertFalse("370.009.100-11".isValidCpf())
        assertFalse("753.290.460-17".isValidCpf())
    }

    @Test
    fun `test isValidCpf values with wrong formmat`() {
        assertFalse("226.843-58".isValidCpf())
        assertFalse("370-009-100.11".isValidCpf())
        assertFalse("753290.460-17".isValidCpf())
    }

}