package com.jeanjnap.bankapp.ui.login

import com.jeanjnap.bankapp.ui.ViewModelBaseTest
import com.jeanjnap.bankapp.util.extension.getOrAwaitValue
import com.jeanjnap.domain.entity.ErrorResponse
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.usecase.BankUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class LoginViewModelTest : ViewModelBaseTest() {

    private lateinit var viewModel: LoginViewModel

    @MockK
    private lateinit var bankUseCase: BankUseCase

    @Before
    fun setup() {
        viewModel = LoginViewModel(network, bankUseCase)
    }

    @Test
    fun onCreate_with_savedUser_shouldSetValues() {
        every { bankUseCase.getUser() } returns anyString()

        viewModel.onCreate()

        assertEquals(anyString(), viewModel.username.getOrAwaitValue())
    }

    @Test
    fun onCreate_without_savedUser_shouldNotSetValues() {
        every { bankUseCase.getUser() } returns null

        viewModel.onCreate()

        assertNull(viewModel.username.value)
    }

    @Test
    fun onUsernameChanged_shouldCleanUsernameError() {
        viewModel.onUserNameChanged(anyString())

        assertFalse(viewModel.usernameError.getOrAwaitValue())
    }

    @Test
    fun onPasswordChanged_shouldCleanPasswordError() {
        viewModel.onPasswordChanged(anyString())

        assertFalse(viewModel.passwordError.getOrAwaitValue())
    }

    @Test
    fun onLoginClick_withInvalidUsername_shouldSetUsernameError() {
        viewModel.onPasswordChanged("aA!")
        viewModel.onLoginClick()

        assertTrue(viewModel.usernameError.getOrAwaitValue())
    }

    @Test
    fun onLoginClick_withInvalidPassword_shouldSetPasswordError() {
        viewModel.onUserNameChanged("user123@test.com")
        viewModel.onLoginClick()

        assertTrue(viewModel.passwordError.getOrAwaitValue())
    }

    @Test
    fun onLoginClick_withInvalidValuesAndSuccessResponse_shouldSetLoginSuccess() {
        coEvery { bankUseCase.login(any(), any()) } returns SuccessResponse(mockk())

        viewModel.onUserNameChanged("user123@test.com")
        viewModel.onPasswordChanged("aA!")
        viewModel.onLoginClick()

        assertNotNull(viewModel.loginSuccess.getOrAwaitValue())
    }

    @Test
    fun onLoginClick_withInvalidValuesAndErrorResponse_shouldSetError() {
        every { resourcesString.genericError } returns anyString()
        coEvery { bankUseCase.login(any(), any()) } returns ErrorResponse(mockk())

        viewModel.onUserNameChanged("user123@test.com")
        viewModel.onPasswordChanged("aA!")
        viewModel.onLoginClick()

        assertEquals(anyString(), viewModel.error.getOrAwaitValue())
    }
}