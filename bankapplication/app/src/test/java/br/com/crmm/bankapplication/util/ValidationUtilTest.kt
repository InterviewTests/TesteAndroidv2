package br.com.crmm.bankapplication.util

import br.com.crmm.bankapplication.Mock
import org.junit.Assert.*
import org.junit.Test

class ValidationUtilTest{

    @Test
    fun checkUsername_isCorrect() {
        with(Mock){
            assertTrue(validationUtils.isValidUsername(validCpf))
            assertTrue(validationUtils.isValidUsername(validEmail))
            assertFalse(validationUtils.isValidUsername(invalidData))
        }
    }

    @Test
    fun checkCpf_isCorrect() {
        with(Mock){
            assertTrue(validationUtils.isValidCpf(validCpf))
        }
    }

    @Test
    fun checkEmail_isCorrect() {
        with(Mock) {
            assertTrue(validationUtils.isValidEmail(validEmail))
        }
    }
}