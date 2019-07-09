package br.com.learncleanarchitecture.login

import br.com.learncleanarchitecture.login.domain.LoginInteractor
import br.com.learncleanarchitecture.login.presentation.LoginActivity
import br.com.learncleanarchitecture.login.presentation.LoginPresenterInput
import br.com.learncleanarchitecture.login.presentation.LoginResponse
import br.com.learncleanarchitecture.login.presentation.LoginViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Robolectric



@RunWith(RobolectricTestRunner::class)
class LoginInteractionUnitTest {

    @Test
    fun `Quando eu chamar o LoginInteractor, deve consultar pelo viewmodel e retornar os campos no field`() {
        // Given
        val activity = Robolectric.setupActivity(LoginActivity::class.java)

        val loginInteractor = LoginInteractor()
        val loginPresenterInputSpy = LoginPresenterInputSpy()

        loginInteractor.output = loginPresenterInputSpy

        // When
        loginInteractor.verifyHaveUser(LoginViewModel())

        // Then
        //todo: Não trabalhamos com Roboletric aqui, ainda to estudando como fazer pra ele não dar exception na thread
        //todo: Preciso ver como que faz pra este teste passar porque tem uma thread em jogo ai isso gera o erro
        Assert.assertTrue(
            "When verifyHaveUser_with_UserInDb is called "
                    + "Then presentLoginInUserFields should be called",
            loginPresenterInputSpy.presentLoginInUserFieldsIsCalled
        )
    }

    private inner class LoginPresenterInputSpy : LoginPresenterInput {

        internal var presentLoginInUserFieldsIsCalled = false
        internal var loginResponseCopy: LoginResponse? = null

        override fun presentLoginMetaData(response: LoginResponse) {

        }

        override fun showError(errorType: String, msg: String) {
        }

        override fun callLogin() {
        }

        override fun presentLoginInUserFields(loginResponse: LoginResponse) {
            presentLoginInUserFieldsIsCalled = true
            loginResponseCopy = loginResponse
        }
    }
}