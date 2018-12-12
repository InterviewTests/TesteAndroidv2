package br.com.rphmelo.bankapp.login

import android.content.SharedPreferences
import br.com.rphmelo.bankapp.login.api.LoginService
import br.com.rphmelo.bankapp.login.domain.interactor.LoginInteractor
import br.com.rphmelo.bankapp.login.domain.models.LoginRequest
import br.com.rphmelo.bankapp.login.presentation.LoginPresenter
import br.com.rphmelo.bankapp.login.repository.LoginRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class LoginInteractorTest {

    private lateinit var loginInteractor: LoginInteractor
    private lateinit var loginRepository: LoginRepository
    private lateinit var loginService: LoginService
    private lateinit var listener: LoginPresenter
    private lateinit var preferences: SharedPreferences

    private val validPassword = "ABC123#"
    private val validCpf = "12345678909"
    private val validEmail = "test@test.com"

    @Before
    fun setup() {
        preferences = mock(SharedPreferences::class.java)
        loginService = mock(LoginService::class.java)
        loginRepository = mock(LoginRepository::class.java)
        listener = mock(LoginPresenter::class.java)
        loginRepository = LoginRepository(loginService,preferences)

        loginInteractor = LoginInteractor(loginRepository)
    }

    @Test
    fun should_verify_login_was_called_with_empty_user() {
        loginInteractor.login(LoginRequest("", validPassword), listener)
        verify(listener, times(1)).onUserEmptyError()
    }

    @Test
    fun should_verify_login_was_called_with_invalid_user() {
        loginInteractor.login(LoginRequest("Raphael", validPassword), listener)
        verify(listener, times(1)).onUserInvalidError()
    }

    @Test
    fun should_verify_login_was_called_with_empty_password() {
        loginInteractor.login(LoginRequest(validCpf, ""), listener)
        verify(listener, times(1)).onPasswordEmptyError()
    }

    @Test
    fun should_verify_login_was_called_with_invalid_password() {
        loginInteractor.login(LoginRequest(validEmail, "123"), listener)
        verify(listener, times(1)).onPasswordInvalidError()
    }
}