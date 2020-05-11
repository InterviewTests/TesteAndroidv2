package com.lucianogiardino.bankapp

import com.lucianogiardino.bankapp.di.appModule
import com.lucianogiardino.bankapp.domain.usecase.ValidateUsernameUseCase
import com.lucianogiardino.bankapp.presentation.login.LoginContract
import com.nhaarman.mockitokotlin2.eq
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidateUsernameUseCaseTest {


    @Mock
    private lateinit var presenter: LoginContract.Presenter.OnValidateUsernameResponse

    @InjectMocks
    private var validateUsernaneUseCase: ValidateUsernameUseCase = ValidateUsernameUseCase()

    @Before
    fun setup() {
        startKoin { modules(appModule) }
        MockitoAnnotations.initMocks(this)

    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun executeWithEmailSuccessTest() {

        var username = "luciano.giardino@ibm.com"
        validateUsernaneUseCase.execute(presenter,username)
        Mockito.verify(presenter).onValidateUsernameResponseSuccess(eq(true))

    }

    @Test
    fun executeWithCpfSuccessTest() {

        var username = "12698076763"
        validateUsernaneUseCase.execute(presenter,username)
        Mockito.verify(presenter).onValidateUsernameResponseSuccess(eq(true))

    }

    @Test
    fun executeWithAnyStringFailedTest() {

        var username = "aam"
        validateUsernaneUseCase.execute(presenter,username)
        Mockito.verify(presenter).onValidateUsernameResponseFailed(eq("Digite um e-mail ou cpf"))

    }

}