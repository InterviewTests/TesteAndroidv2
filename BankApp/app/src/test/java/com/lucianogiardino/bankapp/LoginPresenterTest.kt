package com.lucianogiardino.bankapp

import com.lucianogiardino.bankapp.di.appModule
import com.lucianogiardino.bankapp.domain.model.Statement
import com.lucianogiardino.bankapp.ui.login.LoginContract
import com.lucianogiardino.bankapp.ui.login.LoginPresenter
import com.lucianogiardino.bankapp.ui.statement.StatementContract
import com.nhaarman.mockitokotlin2.eq
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
    lateinit var validateUserUseCase: LoginContract.UseCase.ValidateUser

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
            validateUserUseCase,
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
    fun validateUserSuccessTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnValidateUserResponse>()

        presenter.validateUser("teste","Teste@1")
        Mockito.verify(validateUserUseCase).execute(captor.capture(),eq("teste"),eq("Teste@1"))
        captor.firstValue.onValidateUserResponseSuccess("teste","Teste@1")
        presenter.login("teste","Teste@1")

    }

    @Test
    fun validateUserFailedTest() {
        val captor = com.nhaarman.mockitokotlin2.argumentCaptor<LoginContract.Presenter.OnValidateUserResponse>()

        presenter.validateUser("","Teste")
        Mockito.verify(validateUserUseCase).execute(captor.capture(),eq(""),eq("Teste"))
        captor.firstValue.onValidateUserResponseFailed("Erro")
        Mockito.verify(view).showError("Erro")

    }


}