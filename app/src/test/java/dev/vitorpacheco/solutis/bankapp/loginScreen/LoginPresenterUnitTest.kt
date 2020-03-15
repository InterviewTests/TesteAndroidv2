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

//        loginResponse.listOfFlights = FlightWorker().futureFlights()

        val loginPresenterOutputSpy = LoginPresenterOutputSpy()
        loginPresenter.output = WeakReference(loginPresenterOutputSpy)

        loginPresenter.presentLoginData(loginResponse)

        assertTrue(loginPresenterOutputSpy.isdisplayLoginMetaDataCalled)
    }

    @Test
    fun `presentLoginData com valores válidos o método displayLoginData não deve ser chamado`() {
        val loginPresenter = LoginPresenter()
        val loginResponse = LoginResponse()

//        loginResponse.listOfFlights = null

        val loginPresenterOutputSpy = LoginPresenterOutputSpy()
        loginPresenter.output = WeakReference(loginPresenterOutputSpy)

        loginPresenter.presentLoginData(loginResponse)

        assertFalse(loginPresenterOutputSpy.isdisplayLoginMetaDataCalled)
    }

    private class LoginPresenterOutputSpy : LoginActivityInput {

        var isdisplayLoginMetaDataCalled = false
        var loginViewModelCopy: LoginViewModel? = null

        override fun displayLoginData(viewModel: LoginViewModel) {
            isdisplayLoginMetaDataCalled = true
            loginViewModelCopy = viewModel
        }

    }

}
