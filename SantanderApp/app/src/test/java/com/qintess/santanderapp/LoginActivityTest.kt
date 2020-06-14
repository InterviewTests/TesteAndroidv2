package com.qintess.santanderapp

import android.os.Build
import androidx.test.core.app.ActivityScenario.launch
import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.model.CredentialsModel
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
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
@LooperMode(LooperMode.Mode.PAUSED) // FIXME: Remover quando issue for resolvida (https://github.com/robolectric/robolectric/issues/5356)
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

    @Test
    fun getCredentialsErro_isValid() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val interactor = LoginInteractor()
            val request = LoginRequest()

            // Credenciais válidas com e-mail
            activity.usernameField.setText("raphael@email.com")
            activity.passwordField.setText("SantanderApp@1")
            request.username = activity.usernameField
            request.password = activity.passwordField
            Assert.assertNull(interactor.getCredentialsError(request))

            //Credenciais válidas com CPF
            activity.usernameField.setText("373.213.858-50")
            request.username = activity.usernameField
            Assert.assertNull(interactor.getCredentialsError(request))

            //Credenciais inválidas com e-mail inválido
            activity.usernameField.setText("raphael@email")
            request.username = activity.usernameField
            Assert.assertEquals(interactor.getCredentialsError(request), Validator.USER_ERROR)

            //Credenciais inválidas com CPF inválido
            activity.usernameField.setText("000.000.000-00")
            request.username = activity.usernameField
            Assert.assertEquals(interactor.getCredentialsError(request), Validator.USER_ERROR)

            // Credenciais inválidas com e-mail válido e senha inválida
            activity.usernameField.setText("raphael@email.com")
            activity.passwordField.setText("1234")
            request.username = activity.usernameField
            request.password = activity.passwordField
            Assert.assertEquals(interactor.getCredentialsError(request), Validator.PASS_ERROR)

            // Credenciais inválidas com CPF válido e senha inválida
            activity.usernameField.setText("373.213.858-50")
            activity.passwordField.setText("1234")
            request.username = activity.usernameField
            request.password = activity.passwordField
            Assert.assertEquals(interactor.getCredentialsError(request), Validator.PASS_ERROR)
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

            Assert.assertEquals(activity.usernameField.text.toString(), loginActivityOutputSpy.loginRequestCopy?.username?.text.toString())
            Assert.assertEquals(activity.passwordField.text.toString(), loginActivityOutputSpy.loginRequestCopy?.password?.text.toString())
        }
    }

    // Na chamada do login com credenciais válidas é chamado auth
    @Test
    fun onCallLogin_authIsCalled() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val loginActivityOutputSpy = LoginActivityOutputAuthSpy()
            activity.output = loginActivityOutputSpy

            activity.usernameField.setText("raphael@email.com")
            activity.passwordField.setText("SantanderApp@1")

            activity.login()

            Assert.assertTrue(loginActivityOutputSpy.authIsCalled)
        }
    }

    // Ao clicar no botão Login deve chamar método login()
    @Test
    fun onButtonClick_shouldCallLoginMethod() {
        launch(LoginActivity::class.java).onActivity { activity ->
            val loginActivityOutputSpy = LoginActivityOutputSpy()
            activity.output = loginActivityOutputSpy
            activity.createListeners()
            activity.loginButton.performClick()
            Assert.assertTrue(loginActivityOutputSpy.loginIsCalled)
        }
    }

    // showText deve mostrar dialog
    @Test
    fun onShowText_dialogIsShown() {
        launch(LoginActivity::class.java).onActivity { activity ->
            Assert.assertTrue(activity.showAlert(Validator.CREDENTIALS_TITLE_ERROR, Validator.USER_ERROR))
        }
    }

    class LoginActivityOutputSpy: LoginInteractorInput {
        var loginIsCalled = false
        var loginRequestCopy: LoginRequest? = null
        var checkLastUserIsCalled = false

        override fun login(request: LoginRequest) {
            loginIsCalled = true
            loginRequestCopy = request
        }

        override fun auth(credentials: CredentialsModel) { return }

        override fun checkLastUser(username: String?) {
            checkLastUserIsCalled = true
        }

        override fun getCredentialsError(credentials: LoginRequest): String? {
            return null
        }
    }

    class LoginActivityOutputAuthSpy: LoginInteractor() {
        var authIsCalled = false
        var credentialsCopy: CredentialsModel? = null

        override fun auth(credentials: CredentialsModel) {
            credentialsCopy = credentials
            authIsCalled = true
        }
    }

    class LoginInteractorInputSpy: LoginInteractor() {
        var validationMethodIsCalled = false
        override fun getCredentialsError(credentials: LoginRequest): String? {
            validationMethodIsCalled = true
            return super.getCredentialsError(credentials)
        }
    }
}