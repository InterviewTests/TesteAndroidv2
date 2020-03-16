package dev.vitorpacheco.solutis.bankapp.loginScreen

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.ref.WeakReference

class LoginPresenterUnitTest {

    @Test
    fun `presentLoginData com valores válidos o método displayLoginData deve ser chamado`() {
        val loginPresenter = LoginPresenter()
        val loginResponse = LoginResponse()

        val loginPresenterOutputSpy = LoginPresenterOutputSpy()
        loginPresenter.output = WeakReference(loginPresenterOutputSpy)

        loginPresenter.presentLoginData(loginResponse)

        assertTrue(loginPresenterOutputSpy.isDisplayLoginMetaDataCalled)
    }

    @Test
    fun `presentLoginData com valores inválidos o método displayLoginData não deve ser chamado`() {
        val loginPresenter = LoginPresenter()

        val loginPresenterOutputSpy = LoginPresenterOutputSpy()
        loginPresenter.output = WeakReference(loginPresenterOutputSpy)

        loginPresenter.presentLoginData(null)

        assertFalse(loginPresenterOutputSpy.isDisplayLoginMetaDataCalled)
    }

    private class LoginPresenterOutputSpy : LoginActivityInput {

        var isDisplayLoginMetaDataCalled = false
        var loginViewModelCopy: LoginViewModel? = null

        override fun displayLoginData(viewModel: LoginViewModel) {
            isDisplayLoginMetaDataCalled = true
            loginViewModelCopy = viewModel
        }

    }

}
