package br.com.cauejannini.testesantander

import android.content.Context
import android.content.Intent
import android.os.Build
import br.com.cauejannini.testesantander.commons.form.AppButton
import br.com.cauejannini.testesantander.commons.form.InputTextField
import br.com.cauejannini.testesantander.commons.form.validators.PasswordValidator
import br.com.cauejannini.testesantander.commons.form.validators.UsernameValidator
import br.com.cauejannini.testesantander.login.LoginActivity
import br.com.cauejannini.testesantander.login.LoginInteractorInput
import br.com.cauejannini.testesantander.login.LoginRequest
import br.com.cauejannini.testesantander.login.UserAccount
import br.com.cauejannini.testesantander.statements.StatementsActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class LoginActivityUnitTest {

    @Test
    fun loginActivity_shouldNot_beNull() {
        val activity = Robolectric.setupActivity(LoginActivity::class.java)

        Assert.assertNotNull(activity)
    }

    @Test
    fun onCreate_shouldAdd_inputValidation() {

        val activity = Robolectric.setupActivity(LoginActivity::class.java)

        val inputUser = activity.findViewById<InputTextField>(R.id.inputUser)
        val inputPassword = activity.findViewById<InputTextField>(R.id.inputPassword)

        Assert.assertTrue(inputUser.myValidator is UsernameValidator)
        Assert.assertTrue(inputPassword.myValidator is PasswordValidator)
    }

    @Test
    fun inputUser_shouldOnly_acceptValidUsers() {
        val activity = Robolectric.setupActivity(LoginActivity::class.java)

        val inputUser = activity.findViewById<InputTextField>(R.id.inputUser)

        // Emails inválidos
        inputUser.text = ""
        Assert.assertFalse(inputUser.isValid)

        inputUser.text = "a"
        Assert.assertFalse(inputUser.isValid)

        inputUser.text = "abcdefg123"
        Assert.assertFalse(inputUser.isValid)

        inputUser.text = "abcdefg123@domain"
        Assert.assertFalse(inputUser.isValid)

        inputUser.text = "abcdefg123@domain."
        Assert.assertFalse(inputUser.isValid)

        // Email válido
        inputUser.text = "abcdefg123@domain.com"
        Assert.assertTrue(inputUser.isValid)

        // CPFs inválidos
        inputUser.text = "406361858"
        Assert.assertFalse(inputUser.isValid)

        inputUser.text = "40636185875"
        Assert.assertFalse(inputUser.isValid)

        // CPFs válido
        inputUser.text = "40636185877"
        Assert.assertTrue(inputUser.isValid)

    }

    @Test
    fun inputPassword_shouldOnly_acceptValidPasswords() {
        val activity = Robolectric.setupActivity(LoginActivity::class.java)

        val inputPassword = activity.findViewById<InputTextField>(R.id.inputPassword)

        // Passwords inválidos
        inputPassword.text = ""
        Assert.assertFalse(inputPassword.isValid)

        inputPassword.text = "a"
        Assert.assertFalse(inputPassword.isValid)

        inputPassword.text = "a1"
        Assert.assertFalse(inputPassword.isValid)

        inputPassword.text = "a1@"
        Assert.assertFalse(inputPassword.isValid)

        // Password válido
        inputPassword.text = "a1@B"
        Assert.assertTrue(inputPassword.isValid)

    }

    @Test
    fun btLoginClick_callsLogin_withCorrectData() {

        val activity = Robolectric.setupActivity(LoginActivity::class.java)
        val outputSpy = LoginActivityOutputSpy()
        activity.output = outputSpy

        val inputUser = activity.findViewById<InputTextField>(R.id.inputUser)
        val inputPassword = activity.findViewById<InputTextField>(R.id.inputPassword)
        val btLogin = activity.findViewById<AppButton>(R.id.btLogin)

        inputUser.text = "user@domain.com"
        inputPassword.text = "b2#C"

        btLogin.performClick()

        // Assert login is called in spy
        Assert.assertTrue(outputSpy.loginCalled)

        // Assert request passed is not null and correct
        Assert.assertNotNull(outputSpy.loginRequestCopy)
        Assert.assertTrue(outputSpy.loginRequestCopy.user == inputUser.text)
        Assert.assertTrue(outputSpy.loginRequestCopy.password == inputPassword.text)
    }

    @Test
    fun successfullLogin_shouldCall_statementsActivityWithIntent() {
        val activity = Robolectric.setupActivity(LoginActivity::class.java)

        val userAccount = UserAccount()
        userAccount.userId = 1
        userAccount.name = "Lucio dos Santos Teste"
        userAccount.agency = "1234"
        userAccount.bankAccount = "567890"
        userAccount.balance = 990.34

        activity.onLoggedIn(userAccount)

        val intentEsperado = Intent(activity, StatementsActivity::class.java)
        intentEsperado.putExtra(StatementsActivity.EXTRA_KEY_USER_ACCOUNT, userAccount)

        val intentEnviado = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity

        // Assert redirecting to correct Activity
        Assert.assertEquals(intentEnviado.component?.className, StatementsActivity::class.java.name)

        // Assert that has added extra with correct key
        Assert.assertTrue(intentEnviado.hasExtra(StatementsActivity.EXTRA_KEY_USER_ACCOUNT))

        // Assert that extra type is correct
        Assert.assertTrue(intentEnviado.getSerializableExtra(StatementsActivity.EXTRA_KEY_USER_ACCOUNT) is UserAccount)

    }

    private class LoginActivityOutputSpy: LoginInteractorInput {

        var loginCalled = false
        lateinit var loginRequestCopy: LoginRequest

        override fun login(request: LoginRequest) {
            loginCalled = true
            loginRequestCopy = request

        }
    }

}