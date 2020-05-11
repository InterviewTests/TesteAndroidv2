package com.lucianogiardino.bankapp

import com.lucianogiardino.bankapp.di.appModule
import com.lucianogiardino.bankapp.domain.model.User
import com.lucianogiardino.bankapp.domain.model.UserAccount
import com.lucianogiardino.bankapp.domain.usecase.ValidateUsernameUseCase
import com.lucianogiardino.bankapp.presentation.login.LoginContract
import com.lucianogiardino.bankapp.presentation.login.LoginPresenter
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {
    @Mock
    lateinit var view: LoginContract.View

    @Mock
    lateinit var validatePasswordUseCase: LoginContract.UseCase.ValidatePassword

    @Mock
    lateinit var validateUsernameUseCase: LoginContract.UseCase.ValidateUsername

    @Mock
    lateinit var loginUseCase: LoginContract.UseCase.LoginUser

    @Mock
    lateinit var hasLoggedUserUseCase: LoginContract.UseCase.HasLoggedUser

    @Mock
    lateinit var saveLoggedUserUseCase: LoginContract.UseCase.SaveLoggedUser


    private lateinit var presenter: LoginPresenter

    @Before
    fun setup() {
        startKoin { modules(appModule) }
        MockitoAnnotations.initMocks(this)
        presenter = LoginPresenter(
            view,
            validatePasswordUseCase,
            validateUsernameUseCase,
            loginUseCase,
            hasLoggedUserUseCase,
            saveLoggedUserUseCase
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun validateUsernameSuccessTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnValidateUsernameResponse>()

        var username = "luciano.giardino@gibm.com"
        presenter.validateUsername(username)
        Mockito.verify(validateUsernameUseCase).execute(captor.capture(),eq(username))

    }

    @Test
    fun validateUsernameFailedTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnValidateUsernameResponse>()

        var username = "aaa"
        presenter.validateUsername(username)
        Mockito.verify(validateUsernameUseCase).execute(captor.capture(),eq(username))
        captor.firstValue.onValidateUsernameResponseFailed("mensagem de erro")
        Mockito.verify(view).showUsernameError(true,"mensagem de erro")

    }


    @Test
    fun validatePasswordSuccessTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnValidatePasswordResponse>()

        var password = "Teste@123"
        presenter.validatePassword(password)
        Mockito.verify(validatePasswordUseCase).execute(captor.capture(),eq(password))

    }

    @Test
    fun validatePasswordFailedTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnValidatePasswordResponse>()

        var password = "Teste@123"
        presenter.validatePassword(password)
        Mockito.verify(validatePasswordUseCase).execute(captor.capture(),eq(password))
        captor.firstValue.onValidatePasswordResponseFailed("mensagem de erro")
        Mockito.verify(view).showPasswordError(true,"mensagem de erro")

    }

    @Test
    fun loginSuccess() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnLoginResponse>()
        var userAccount = UserAccount(1,"Luciano",10,383,10500.50)
        var username = "luciano.giardino@gibm.com"
        var password = "Teste@123"
        presenter.login(username,password)
        Mockito.verify(loginUseCase).execute(captor.capture(),eq(username),eq(password))
        captor.firstValue.onLoginResponseSuccess(userAccount)
        Mockito.verify(view).goToStatement()

    }


    @Test
    fun loginFailed() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnLoginResponse>()
        var username = "luciano.giardino@gibm.com"
        var password = "Teste@123"
        presenter.login(username,password)
        Mockito.verify(loginUseCase).execute(captor.capture(),eq(username),eq(password))
        captor.firstValue.onLoginResponseFailed("Erro no login")
        Mockito.verify(view).showError("Erro no login")

    }


}