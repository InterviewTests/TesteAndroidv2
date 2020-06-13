package com.qintess.santanderapp

import android.os.Build
import androidx.test.core.app.ActivityScenario.launch
import com.qintess.santanderapp.ui.view.loginScreen.LoginActivity
import com.qintess.santanderapp.ui.view.loginScreen.LoginInteractor
import com.qintess.santanderapp.ui.view.loginScreen.LoginInteractorInput
import com.qintess.santanderapp.ui.view.loginScreen.LoginRequest
import kotlinx.android.synthetic.main.activity_login.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class LoginActivityTest {
    // LoginActivity não é null
    @Test
    fun loginActivity_isNotNull() {
        launch(LoginActivity::class.java).onActivity { activity ->
            Assert.assertNotNull(activity)
        }
    }

    // usernameField não é null
    @Test
    fun user_field_isNotNull() {
        launch(LoginActivity::class.java).onActivity { activity ->
            Assert.assertNotNull(activity.usernameField)
        }
    }

    // passwordField não é null
    @Test
    fun pass_field_isNotNul() {
        launch(LoginActivity::class.java).onActivity { activity ->
            Assert.assertNotNull(activity.passwordField)
        }
    }

    // usernameField está validando e-mail
    @Test
    fun user_field_isValidatingEmail() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val userField = activity.usernameField

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
    }

    // usernameField está validando CPF
    @Test
    fun user_field_isValidatingCPF() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val userField = activity.usernameField

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
    }

    // passwordField está validando
    @Test
    fun pass_field_isValidating() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val passwordField = activity.passwordField

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

    // No onCreate da activiy deve-se chamar checkLastUser
    @Test
    fun onCreate_shouldCallCheckLastUser() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val loginActivityOutputSpy = LoginActivityOutputSpy()
            activity.output = loginActivityOutputSpy

            activity.checkLastUser()

            Assert.assertTrue(loginActivityOutputSpy.checkLastUserIsCalled)
        }
    }

    // No login deve-se chamar validação
    @Test
    fun onLogin_shouldCallValidation() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val loginActivityOutputSpy = LoginInteractorInputSpy()
            activity.output = loginActivityOutputSpy

            activity.login()

            Assert.assertTrue(loginActivityOutputSpy.validationMethodIsCalled)
        }
    }

    // Na chamada do login, os valores dos campos são passados corretamente
    @Test
    fun onCallLogin_fieldsValuesArePassedCorrectly() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val loginActivityOutputSpy = LoginActivityOutputSpy()
            activity.output = loginActivityOutputSpy

            activity.usernameField.setText("raphael@email.com")
            activity.passwordField.setText("SantanderApp@1")

            activity.login()

            Assert.assertEquals(activity.usernameField.text.toString(), loginActivityOutputSpy.loginRequestCopy?.username)
            Assert.assertEquals(activity.passwordField.text.toString(), loginActivityOutputSpy.loginRequestCopy?.password)
        }
    }

    class LoginActivityOutputSpy: LoginInteractorInput {
        var loginRequestCopy: LoginRequest? = null
        var checkLastUserIsCalled = false

        override fun login(request: LoginRequest) {
            loginRequestCopy = request
        }

        override fun checkLastUser() {
            checkLastUserIsCalled = true
        }

        override fun getLastUser(): String? {
            return null
        }

        override fun isCredentialsValid(credentials: LoginRequest): Boolean {
            return true
        }
    }

    class LoginInteractorInputSpy: LoginInteractor() {
        var validationMethodIsCalled = false
        override fun isCredentialsValid(credentials: LoginRequest): Boolean {
            validationMethodIsCalled = true
            return super.isCredentialsValid(credentials)
        }
    }
}