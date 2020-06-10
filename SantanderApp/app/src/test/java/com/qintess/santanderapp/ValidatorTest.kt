package com.qintess.santanderapp

import com.qintess.santanderapp.helper.Validator
import org.junit.Assert
import org.junit.Test

class ValidatorTest {
    @Test
    fun email_validation_isValid() {
        // E-mail válido
        Assert.assertTrue(Validator.isEmailValid("raphael@email.com"))

        // E-mails inválidos
        Assert.assertFalse(Validator.isEmailValid("raphael@email"))
        Assert.assertFalse(Validator.isEmailValid("raphael.com"))
        Assert.assertFalse(Validator.isEmailValid("**@**.com"))
        Assert.assertFalse(Validator.isEmailValid(""))
    }

    @Test
    fun cpf_validation_isValid() {
        // CPF válido
        Assert.assertTrue(Validator.isCpfValid("373.213.858-50"))

        // CPFs inválidos
        Assert.assertFalse(Validator.isCpfValid("000.000.000-00"))
        Assert.assertFalse(Validator.isCpfValid("1234"))
        Assert.assertFalse(Validator.isCpfValid("ABC"))
        Assert.assertFalse(Validator.isCpfValid(""))
    }

    @Test
    fun pass_validation_isValid() {
        // Senha válida
        Assert.assertTrue(Validator.isPasswordValid("AppSantander123@"))

        //Senhas inválidas
        Assert.assertFalse(Validator.isPasswordValid("appsantander123@"))
        Assert.assertFalse(Validator.isPasswordValid("AppSantander123"))
        Assert.assertFalse(Validator.isPasswordValid("AppSantander@"))
        Assert.assertFalse(Validator.isPasswordValid(""))
    }
}