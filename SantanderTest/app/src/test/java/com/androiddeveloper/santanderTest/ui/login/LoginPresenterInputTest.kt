package com.androiddeveloper.santanderTest.ui.login

import com.androiddeveloper.santanderTest.data.model.userdata.LoginError
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
class LoginPresenterInputTest {

    val presenter = LoginPresenterInput()
    val spy = LoginActivitySpy()

    @Before
    @Throws(Exception::class)
    fun setup(){
        val weakReference = WeakReference<ILoginContract.LoginActInput>(spy)
        presenter.output = weakReference
    }

    @Test
    @Throws(Exception::class)
    fun isUsernameInvalid() {
        presenter.isUserValid("0123", "11a@A")
        assertTrue("Invalid username", spy.isOnInvalidLoginCalled)
    }

    @Test
    @Throws(Exception::class)
    fun isPasswordInvalid() {
        presenter.isUserValid("01235678911", "11")
        assertTrue("Invalid password", spy.isOnInvalidLoginCalled)
    }

    @Test
    @Throws(Exception::class)
    fun isPasswordAndUsernameInvalid() {
        presenter.isUserValid("0123567", "11")
        assertTrue("Invalid username and password", spy.isOnInvalidLoginCalled)
    }

    @Test
    @Throws(Exception::class)
    fun isUserValid() {
        presenter.isUserValid("01235678911", "1A@")
        assertTrue("login success", spy.isOnValidLoginCalled)
    }

    class LoginActivitySpy : ILoginContract.LoginActInput {
        var isLoginErrorCalled = false
        var isLoginSuccessCalled = false
        var isOnInvalidLoginCalled = false
        var isOnValidLoginCalled = false

        override fun onLoginError(err: LoginError) {
            isLoginErrorCalled = true
        }

        override fun onLoginSuccess() {
            isLoginSuccessCalled = true
        }

        override fun onInvalidLogin(message: Int) {
            isOnInvalidLoginCalled = true
        }

        override fun onValidLogin(username: String, password: String) {
            isOnValidLoginCalled = true
        }

    }
}