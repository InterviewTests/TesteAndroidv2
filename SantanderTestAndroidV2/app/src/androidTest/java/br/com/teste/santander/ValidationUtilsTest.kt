package br.com.teste.santander

import br.com.teste.santander.utils.ValidationUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun testValidateCpf() {
        assertTrue(ValidationUtils.isValidCpf("24549127406"))
        assertTrue(ValidationUtils.isValidCpf("245.491.274-06"))
        assertFalse(ValidationUtils.isValidCpf("245.491.274-6"))
        assertFalse(ValidationUtils.isValidCpf("123.456.789.10"))
        assertFalse(ValidationUtils.isValidCpf("aaa.aaa.aaa-aa"))
    }

    @Test
    fun testValidateEmail() {
        assertTrue(ValidationUtils.isValidEmail("teste@teste.com"))
        assertTrue(ValidationUtils.isValidEmail("teste@teste.com.br"))
        assertTrue(ValidationUtils.isValidEmail("teste.teste@teste.com"))
        assertTrue(ValidationUtils.isValidEmail("teste123@teste.com"))
        assertFalse(ValidationUtils.isValidEmail("teste@teste."))
        assertFalse(ValidationUtils.isValidEmail("teste.teste.com"))
        assertFalse(ValidationUtils.isValidEmail("teste@teste@teste.com"))
    }
    
    @Test
    fun testValidatePassword() {
        assertTrue(ValidationUtils.isValidPassword("T&este123"))
        assertTrue(ValidationUtils.isValidPassword("@asR123"))
        assertTrue(ValidationUtils.isValidPassword("3%Ui"))
        assertTrue(ValidationUtils.isValidPassword("tesT&123"))
        assertFalse(ValidationUtils.isValidPassword(""))
        assertFalse(ValidationUtils.isValidPassword("#34a"))
        assertFalse(ValidationUtils.isValidPassword("#34A"))
        assertFalse(ValidationUtils.isValidPassword("#Aa"))
        assertFalse(ValidationUtils.isValidPassword("T34a"))
    }

    @Test
    fun testValidateUser() {
        assertTrue(ValidationUtils.isValidUserName("teste@teste.com"))
        assertTrue(ValidationUtils.isValidUserName("teste@teste.com.br"))
        assertTrue(ValidationUtils.isValidUserName("teste.teste@teste.com"))
        assertTrue(ValidationUtils.isValidUserName("teste123@teste.com"))
        assertFalse(ValidationUtils.isValidUserName("teste@teste."))
        assertFalse(ValidationUtils.isValidUserName("teste.teste.com"))
        assertFalse(ValidationUtils.isValidUserName("teste@teste@teste.com"))
        assertTrue(ValidationUtils.isValidUserName("24549127406"))
        assertTrue(ValidationUtils.isValidUserName("245.491.274-06"))
        assertFalse(ValidationUtils.isValidUserName("245.491.274-6"))
        assertFalse(ValidationUtils.isValidUserName("123.456.789.10"))
        assertFalse(ValidationUtils.isValidUserName("aaa.aaa.aaa-aa"))
    }
}