package br.com.bankapp.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.bankapp.BaseViewModelTest
import br.com.bankapp.commons.Success
import br.com.bankapp.domain.usecases.LoginUseCase
import br.com.bankapp.ui.login.LoginViewModel
import br.com.fortes.appcolaborador.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class LoginViewModelTest : BaseViewModelTest() {

    private var loginUseCase = mockk<LoginUseCase>()
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(loginUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeAttemptLogin_setsUiStatusEvent() {
        runBlockingTest {
            coEvery { loginUseCase(any(), any()) } returns Unit
            loginViewModel.executeAttemptLogin("user", "password")
            val uiStatus = loginViewModel.uiState.getOrAwaitValue()
            assertThat(uiStatus, instanceOf(Success::class.java))
        }
    }
}