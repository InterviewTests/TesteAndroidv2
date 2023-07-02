package com.nandoligeiro.safrando.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nandoligeiro.safrando.domain.login.model.LoginDomain
import com.nandoligeiro.safrando.domain.login.result.LoginResult
import com.nandoligeiro.safrando.domain.login.usecase.postLogin.PostLoginUseCase
import com.nandoligeiro.safrando.domain.login.usecase.postLogin.PostLoginUseCaseImpl
import com.nandoligeiro.safrando.presentation.login.mapper.LoginDomainToUiMapper
import com.nandoligeiro.safrando.presentation.login.model.UiLoginModel
import com.nandoligeiro.safrando.presentation.login.state.LoginState
import com.nandoligeiro.safrando.presentation.login.viewmodel.LoginViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    companion object {
        const val user = "919.491.310-25"
        const val password = "Safra@2023"
    }

    private lateinit var viewModel: LoginViewModel

    @MockK
    private lateinit var postLoginUseCase: PostLoginUseCase

    @MockK
    private lateinit var loginDomainToUiMapper: LoginDomainToUiMapper

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        postLoginUseCase = spyk(PostLoginUseCaseImpl(mockk(), mockk(), mockk(), mockk()))
        loginDomainToUiMapper = LoginDomainToUiMapper()
        viewModel = LoginViewModel(postLoginUseCase, loginDomainToUiMapper)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `SIGN-IN - RESULT SUCCESS `() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        coEvery { postLoginUseCase(any(), any()) } returns LoginResult.Success(resultLoginDomain())

        viewModel.signIn(user, password)

        coVerify { postLoginUseCase(user, password) }

        assert(viewModel.login.value == LoginState.Success(getUiLoginModel()))
    }

    @Test
    fun `SIGN-IN - RESULT Check Field Error `() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        coEvery { postLoginUseCase(any(), any()) } returns LoginResult.CheckFieldError

        viewModel.signIn(user, password)

        coVerify { postLoginUseCase(user, password) }

        assert(viewModel.login.value == LoginState.CheckFieldError)
    }

    @Test
    fun `SIGN-IN - RESULT Error `() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        coEvery { postLoginUseCase(any(), any()) } returns LoginResult.Error(Exception())

        viewModel.signIn(user, password)

        coVerify { postLoginUseCase(user, password) }

        assert(viewModel.login.value == LoginState.Error)
    }


    private fun resultLoginDomain() = LoginDomain(
        id = 1,
        name = "Jose da Silva Teste",
        agency = "2050",
        account = "01.11122-4",
        balance = "R$ 1.000,00"
    )

    private fun getUiLoginModel() = UiLoginModel(
        id = 1,
        name = "Jose da Silva Teste",
        agency = "2050",
        account = "01.11122-4",
        balance = "R$ 1.000,00"
    )

}
