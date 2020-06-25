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
import br.com.cauejannini.testesantander.statements.StatementsInteractorInput
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
class StatementsActivityUnitTest {

    @Test
    fun statementsActivity_shouldNot_beNull() {
        val activity = Robolectric.setupActivity(StatementsActivity::class.java)

        Assert.assertNotNull(activity)
    }

    @Test
    fun onCreate_fetchStatements_withCorrectData() {

        val activity = Robolectric.setupActivity(StatementsActivity::class.java)
        val outputSpy = StatementsInteractorInputSpy()
        activity.output = outputSpy

        val userAccount = UserAccount()
        userAccount.userId = 1
        userAccount.name = "Lucio dos Santos Teste"
        userAccount.agency = "1234"
        userAccount.bankAccount = "567890"
        userAccount.balance = 990.34

        activity.fetchStatements(userAccount)

        // Assert login is called in spy
        Assert.assertTrue(outputSpy.fetchStatementsCalled)

        // Assert request passed is not null and correct
        Assert.assertEquals(outputSpy.userAccountCopy, userAccount)
    }

    private class StatementsInteractorInputSpy: StatementsInteractorInput {

        var fetchStatementsCalled = false
        var userAccountCopy: UserAccount? = null

        override fun fetchStatements(userAccount: UserAccount?) {
            fetchStatementsCalled = true
            userAccountCopy = userAccount
        }
    }

}