package br.com.learncleanarchitecture.login

import br.com.learncleanarchitecture.AppController
import br.com.learncleanarchitecture.login.data.room.LoginTaskDAO
import br.com.learncleanarchitecture.login.domain.LoginInteractorInput
import br.com.learncleanarchitecture.login.presentation.LoginActivity
import br.com.learncleanarchitecture.login.presentation.LoginFragment
import br.com.learncleanarchitecture.login.presentation.LoginRequest
import br.com.learncleanarchitecture.login.presentation.LoginViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class LoginFragmentUnitTest {

    @Before
    fun setUp() {

        val app = RuntimeEnvironment.application as AppController

        val database = LoginTaskDAO.getInstanceRoom(RuntimeEnvironment.application)

        app.setDb(database)

    }


    @Test
    fun mainActivity_ShouldNOT_be_Null() {
        // Given
        val activity = Robolectric.setupActivity(LoginActivity::class.java)
        // When

        // Then
        Assert.assertNotNull(activity)
    }

    @Test
    fun fragment_ShouldNOT_be_Null() {
        // Given
        val fragment = LoginFragment()
        // When

        // Then
        Assert.assertNotNull(fragment)
    }

    @Test
    fun onCreateView_shouldCall_verifyHaveUser() {
        // Given
        val loginOutputSpy = LoginOutputSpy()

        // It must have called the onCreateView earlier,
        // we are injecting the mock and calling the fetchData to test our condition
        val loginFragment = LoginFragment()
        loginFragment.output = loginOutputSpy
        loginFragment.viewModel = LoginViewModel()

        // When
        loginFragment.verifyHaveUser()

        // Then
        Assert.assertTrue(loginOutputSpy.verifyHaveUserDataIsCalled)
    }

    private inner class LoginOutputSpy: LoginInteractorInput {

        var verifyHaveUserDataIsCalled = false
        lateinit var loginRequestCopy: LoginRequest

        override fun verifyHaveUser(viewModel: LoginViewModel) {
            verifyHaveUserDataIsCalled = true
        }

        override fun validateFields(username: String, password: String) {

        }

        override fun doLogin(request: LoginRequest) {

        }

    }
}