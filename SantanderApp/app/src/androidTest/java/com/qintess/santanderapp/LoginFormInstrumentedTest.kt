package com.qintess.santanderapp

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.qintess.santanderapp.ui.components.PasswordField
import com.qintess.santanderapp.ui.components.UserField
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginFormInstrumentedTest {
    lateinit var ctx: Context

    @Before
    fun setup() {
        ctx = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun user_field_isNotNull() {
        val userField = UserField(ctx)
        Assert.assertNotNull(userField)
    }

    @Test
    fun user_field_isValidatingEmail() {
        val userField = UserField(ctx)

        //E-mail válido
        userField.setText("raphael@email.com")
        Assert.assertTrue(userField.valid)

        // E-mails inválidos
        userField.setText("raphael@email")
        Assert.assertFalse(userField.valid)

        userField.setText("raphael.com")
        Assert.assertFalse(userField.valid)

        userField.setText("**@**.com")
        Assert.assertFalse(userField.valid)

        userField.setText("")
        Assert.assertFalse(userField.valid)
    }

    @Test
    fun user_field_isValidatingCPF() {
        val userField = UserField(ctx)

        // CPF válido
        userField.setText("373.213.858-50")
        Assert.assertTrue(userField.valid)

        // CPFs inválidos
        userField.setText("000.000.000-00")
        Assert.assertFalse(userField.valid)

        userField.setText("1234")
        Assert.assertFalse(userField.valid)

        userField.setText("ABC")
        Assert.assertFalse(userField.valid)

        userField.setText("")
        Assert.assertFalse(userField.valid)
    }

    @Test
    fun pass_field_isNotNul() {
        val passwordField = PasswordField(ctx)
        Assert.assertNotNull(passwordField)
    }

    @Test
    fun pass_field_isValidating() {
        val passwordField = PasswordField(ctx)

        // Senha válida
        passwordField.setText("AppSantander123@")
        Assert.assertTrue(passwordField.valid)

        //Senhas inválidas
        passwordField.setText("appsantander123@")
        Assert.assertFalse(passwordField.valid)
        passwordField.setText("AppSantander123")
        Assert.assertFalse(passwordField.valid)
        passwordField.setText("AppSantander@")
        Assert.assertFalse(passwordField.valid)
        passwordField.setText("")
    }
}