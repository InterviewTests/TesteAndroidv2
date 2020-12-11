package br.com.cauejannini.testesantander

import android.os.Build
import br.com.cauejannini.testesantander.login.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.ref.WeakReference

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class LoginPresenterUnitTest {

    @Test
    fun present_correctData_onLoginSuccessfull() {

        val loginPresenter = LoginPresenter()
        val outputSpy = LoginActivityInputSpy()
        loginPresenter.output = WeakReference(outputSpy)

        val loginResponseModel = LoginResponseModel()

        val userAccount = UserAccount()
        userAccount.userId = 1
        userAccount.name = "Lucio dos Santos Teste"
        userAccount.agency = "1234"
        userAccount.bankAccount = "567890"
        userAccount.balance = 990.34

        loginResponseModel.userAccount = userAccount
        loginResponseModel.error = null

        loginPresenter.onLoginResponse(loginResponseModel)

        Assert.assertTrue(outputSpy.onLoggedInCalled)
        Assert.assertEquals(outputSpy.userAccountCopy, userAccount)

        Assert.assertFalse(outputSpy.onLoginErrorCalled)
        Assert.assertNull(outputSpy.errorMessageCopy)

    }

    @Test
    fun present_correctData_onLoginResponseError() {

        val loginPresenter = LoginPresenter()
        val outputSpy = LoginActivityInputSpy()
        loginPresenter.output = WeakReference(outputSpy)

        val loginResponseModel = LoginResponseModel()

        loginResponseModel.userAccount = null

        val errorMessage = "Erro retornado no LoginResponseModel"
        loginResponseModel.error = errorMessage

        loginPresenter.onLoginResponse(loginResponseModel)

        Assert.assertTrue(outputSpy.onLoginErrorCalled)
        Assert.assertEquals(outputSpy.errorMessageCopy, errorMessage)

        Assert.assertFalse(outputSpy.onLoggedInCalled)
        Assert.assertNull(outputSpy.userAccountCopy)

    }

    @Test
    fun present_correctData_onLoginFailed() {

        val loginPresenter = LoginPresenter()
        val outputSpy = LoginActivityInputSpy()
        loginPresenter.output = WeakReference(outputSpy)

        val errorMessage = "Erro de integração"

        loginPresenter.onLoginFailed(errorMessage)

        Assert.assertTrue(outputSpy.onLoginErrorCalled)
        Assert.assertEquals(outputSpy.errorMessageCopy, errorMessage)

        Assert.assertFalse(outputSpy.onLoggedInCalled)
        Assert.assertNull(outputSpy.userAccountCopy)

    }

    private class LoginActivityInputSpy: LoginActivityInput {

        var onLoggedInCalled = false
        var userAccountCopy: UserAccount? = null

        var onLoginErrorCalled = false
        var errorMessageCopy: String? = null

        override fun onLoggedIn(userAccount: UserAccount) {
            onLoggedInCalled = true
            userAccountCopy = userAccount
        }

        override fun onLoginError(message: String) {
            onLoginErrorCalled = true
            errorMessageCopy = message
        }

    }

}