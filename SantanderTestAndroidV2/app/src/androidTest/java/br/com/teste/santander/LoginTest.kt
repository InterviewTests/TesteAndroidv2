package br.com.teste.santander

import android.content.Context
import br.com.teste.santander.login.interactor.LoginInteractorImpl
import br.com.teste.santander.spy.LoginPresenterSpy
import br.com.teste.santander.spy.LoginRepositorySpy
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock


class LoginTest {

    @Test
    fun homeActivity_callLogin() {
        val user = "teste@teste.com"
        val password = "123@Teste"

        val repository = LoginRepositorySpy()

        val interactor = LoginInteractorImpl()
        interactor.repository = repository
        interactor.doLogin(mock(Context::class.java), user, password)

        assertTrue(repository.doLoginCalled)
    }

    @Test
    fun homeActivity_saveUser() {
        val context = mock(Context::class.java)

        val presenter = LoginPresenterSpy()

        val interactor = LoginInteractorImpl()
        interactor.presenter = presenter

        interactor.verifyLastUser(context)

        assertTrue(presenter.setUserCalled)
    }

    @Test
    fun homeActivity_onLoginSuccess() {
        val context = mock(Context::class.java)

        val repository = LoginRepositorySpy()

        val interactor = LoginInteractorImpl()
        interactor.repository = repository

        val user = "teste@teste.com"
        val password = "123@Teste"

        interactor.doLogin(context, user, password)

        assertTrue(repository.doLoginCalled)
        assertTrue(repository.user == user)
        assertTrue(repository.password == password)
    }
}