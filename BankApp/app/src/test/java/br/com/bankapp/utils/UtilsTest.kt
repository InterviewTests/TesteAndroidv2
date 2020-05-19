package br.com.bankapp.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsTest {

    @Test
    fun isCPFValid() {
        val cpfLess11Numbers = "037.176.123-9"
        val cpfHasCharacter = "037.176.123-9s"
        val cpfInvalidDigit10BiggerThenFirstNineth = "000.000.000-94"
        val cpfInvalidDigit10 = "037.176.123-84"
        val cpfInvalidDigit11 = "037.176.123-94"
        val cpfValid = "037.176.123-95"
        Assert.assertFalse(isCPFValid(cpfLess11Numbers))
        Assert.assertFalse(isCPFValid(cpfHasCharacter))
        Assert.assertFalse(isCPFValid(cpfInvalidDigit10BiggerThenFirstNineth))
        Assert.assertFalse(isCPFValid(cpfInvalidDigit10))
        Assert.assertFalse(isCPFValid(cpfInvalidDigit11))
        Assert.assertTrue(isCPFValid(cpfValid))
    }

    @Test
    fun isEmailValid() {
        val validEmail1 = "teste@email.com"
        val validEmail2 = "teste.123@email.com"
        val invalidEmail1 = "teste.email.com"
        val invalidEmail2 = "teste@email"
        Assert.assertFalse(isEmailValid(invalidEmail1))
        Assert.assertFalse(isEmailValid(invalidEmail2))
        Assert.assertTrue(isEmailValid(validEmail1))
        Assert.assertTrue(isEmailValid(validEmail2))
    }

    @Test
    fun isPasswordValid() {
        val validPassword1 = "123K@43"
        val validPassword2 = "asadasK@"
        val invalidPassword1 = "asadasK"
        val invalidPassword2 = "123@4356"
        Assert.assertFalse(isPasswordValid(invalidPassword1))
        Assert.assertFalse(isPasswordValid(invalidPassword2))
        Assert.assertTrue(isPasswordValid(validPassword1))
        Assert.assertTrue(isPasswordValid(validPassword2))
    }
}