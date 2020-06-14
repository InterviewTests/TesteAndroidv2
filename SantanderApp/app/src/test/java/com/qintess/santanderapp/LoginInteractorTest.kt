package com.qintess.santanderapp

import android.os.Build
import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.model.CredentialsModel
import com.qintess.santanderapp.model.UserModel
import com.qintess.santanderapp.service.*
import com.qintess.santanderapp.ui.view.loginScreen.LoginInteractor
import com.qintess.santanderapp.ui.view.loginScreen.LoginPresenterInput
import com.qintess.santanderapp.ui.view.loginScreen.LoginRequest
import org.json.JSONObject
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
    @Mock
    private lateinit var userService: UserService

    // Checagem de último usuário logado com usuário armazenado deve mostrar último usuário no campo
    @Test
    fun checkLastUser_with_stored_user_shouldCallPresentLastUser() {
        interactor = LoginInteractor()

        val loginInteractorOutputSpy = LoginInteractorOutputSpy()
        interactor.output = loginInteractorOutputSpy
        interactor.checkLastUser("raphacmartin")

        Assert.assertTrue(loginInteractorOutputSpy.presentLastUserIsCalled)
    }

    // No erro de credenciais deve-se mostrar mensagem de erro
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

        Assert.assertTrue(loginInteractorOutputSpy.presentLoginErrorMsgIsCalled)
    }

    // Em login com sucesso deve-se mostrar a tela de lançamentos
    @Test
    fun onSuccessfulLogin_shouldPresentStatementsScreen() {
        `when`(userService.getHttpClient())
            .thenReturn(FakeSuccessHttpClient())
        interactor = LoginInteractor()
        interactor.userService = userService

        val loginInteractorOutputSpy = LoginInteractorOutputSpy()
        interactor.output = loginInteractorOutputSpy

        val credentials = CredentialsModel("raphael@email.com", "Santander@1")
        interactor.auth(credentials)

        Assert.assertTrue(loginInteractorOutputSpy.presentStatementScreenIsCalled)
    }

    // Em login com falha deve-se mostrar mensagem de erro
    @Test
    fun onFailureLogin_shouldPresentErrorMessage() {
        `when`(userService.getHttpClient())
            .thenReturn(FakeErrorHttpClient())
        interactor = LoginInteractor()
        interactor.userService = userService

        val loginInteractorOutputSpy = LoginInteractorOutputSpy()
        interactor.output = loginInteractorOutputSpy

        val credentials = CredentialsModel("raphael@email.com", "Santander@1")
        interactor.auth(credentials)

        Assert.assertTrue(loginInteractorOutputSpy.presentLoginErrorMsgIsCalled)
    }

    class LoginInteractorOutputSpy: LoginPresenterInput {
        var presentLastUserIsCalled = false
        var presentLoginErrorMsgIsCalled = false
        var presentStatementScreenIsCalled = false
        override fun presentLastUser(username: String) {
            presentLastUserIsCalled = true
        }

        override fun presentErrorMessage(title: String, msg: String) {
            presentLoginErrorMsgIsCalled = true
        }

        override fun presentStatementScreen(user: UserModel) {
            presentStatementScreenIsCalled = true
        }
    }

    open class FakeSuccessHttpClient: HttpInterface {
        override fun post(url: String, bodyParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onSuccess(
                JSONObject("""
                {
                    "userAccount": {
                        "userId": 1,
                        "name": "Jose da Silva Teste",
                        "bankAccount": "2050",
                        "agency": "012314564",
                        "balance": 3.3445
                    },
                    "error": {}
                }
            """)
            )
        }

        override fun get(url: String, urlParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onFailure(Exception("Nenhum método get para usuários"))
        }
    }

    open class FakeErrorHttpClient: HttpInterface {
        override fun post(url: String, bodyParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onFailure(java.lang.Exception("Erro ao realizar login"))
        }

        override fun get(url: String, urlParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onFailure(Exception("Nenhum método get para usuários"))
        }
    }
}