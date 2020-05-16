package com.lucianogiardino.bankapp

import com.lucianogiardino.bankapp.di.appModule
import com.lucianogiardino.bankapp.domain.usecase.ValidatePasswordUseCase
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
class ValidatePasswordUseCaseTest {
    @Mock
    private lateinit var presenter: LoginContract.Presenter.OnValidatePasswordResponse

    @InjectMocks
    private var validatePasswordUseCase: ValidatePasswordUseCase = ValidatePasswordUseCase()

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
    fun executeWithPasswordFormatSuccessTest() {

        var password = "Teste@123"
        validatePasswordUseCase.execute(presenter,password)
        Mockito.verify(presenter).onValidatePasswordResponseSuccess(eq(true))

    }

    @Test
    fun executeWithPasswordFormatFailedTest() {

        var password = "12698076763"
        validatePasswordUseCase.execute(presenter,password)
        Mockito.verify(presenter).onValidatePasswordResponseFailed(eq("Formato da senha inválido"))

    }


}