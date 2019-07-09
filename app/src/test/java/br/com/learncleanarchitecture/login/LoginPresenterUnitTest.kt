package br.com.learncleanarchitecture.login

import br.com.learncleanarchitecture.login.presentation.*
import org.junit.Assert
import org.junit.Test
import java.lang.ref.WeakReference

class LoginPresenterUnitTest {
    @Test
    fun presentLoginInUserFields_with_userDB_shouldCall_isDisplayLoginMetaDataCalled() {
        // Given
        val loginPresenter = LoginPresenter()
        val loginResponse = LoginResponse()
        loginResponse.login = Login()

        val loginFragmentInputSpy = LoginFragmentInputSpy()
        loginPresenter.output = WeakReference(loginFragmentInputSpy)

        // When
        loginPresenter.presentLoginInUserFields(loginResponse)

        // Then
        Assert.assertTrue("When the valid input is passed to HomePresenter " +
                "Then displayHomeData should be called",
            loginFragmentInputSpy.isDisplayLoginMetaDataCalled)
    }

    private inner class LoginFragmentInputSpy : LoginFragmentInput {

        var isDisplayLoginMetaDataCalled = false
        lateinit var loginViewModelCopy: LoginViewModel

        override fun responseServiceLogin(login: Login?) {

        }

        override fun showLoading() {
        }

        override fun hideLoading() {
        }

        override fun usernameError(error: String) {
        }

        override fun callLogin() {
        }

        override fun passwordError(passwordError: String) {
        }

        override fun showErrorResponse(error: String) {
        }

        override fun putUsernameAndPasswordInFields(loginResponse: LoginResponse) {
            isDisplayLoginMetaDataCalled = true
        }

    }

}