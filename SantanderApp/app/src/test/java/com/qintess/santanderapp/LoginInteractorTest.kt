package com.qintess.santanderapp

import android.os.Build
import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.ui.view.loginScreen.LoginInteractor
import com.qintess.santanderapp.ui.view.loginScreen.LoginPresenterInput
import com.qintess.santanderapp.ui.view.loginScreen.LoginRequest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class LoginInteractorTest {
    @Mock
    private lateinit var interactor: LoginInteractor

    // Checagem de último usuário logado com usuário armazenado deve mostrar último usuário no campo
    @Test
    fun checkLastUser_with_stored_user_shouldCallPresentLastUser() {
        // Mockando para sempre retornar um último usuário logado ao invés de consultar as Prefs
        `when`(interactor.getLastUser())
            .thenReturn("raphacmartin")
        `when`(interactor.checkLastUser())
            .thenCallRealMethod()

        val loginInteractorOutputSpy = LoginInteractorOutputSpy()
        interactor.output = loginInteractorOutputSpy
        interactor.checkLastUser()

        Assert.assertTrue(loginInteractorOutputSpy.presentLastUserIsCalled)
    }

    @Test
    fun onCredentialsError_shouldPresentLoginErrorMessage() {
        val credentials = LoginRequest()
        `when`(interactor.getCredentialsError(credentials))
            .thenReturn(Validator.USER_ERROR)
        `when`(interactor.login(credentials))
            .thenCallRealMethod()
        val loginInteractorOutputSpy = LoginInteractorOutputSpy()
        interactor.output = loginInteractorOutputSpy
        interactor.login(credentials)

        Assert.assertTrue(loginInteractorOutputSpy.presentLoginErrorMsg)
    }

    class LoginInteractorOutputSpy: LoginPresenterInput {
        var presentLastUserIsCalled = false
        var presentLoginErrorMsg = false
        override fun presentLastUser(username: String) {
            presentLastUserIsCalled = true
        }

        override fun presentLoginErrorMessage(msg: String) {
            presentLoginErrorMsg = true
        }
    }
}