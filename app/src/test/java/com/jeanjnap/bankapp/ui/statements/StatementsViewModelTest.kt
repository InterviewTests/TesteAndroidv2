package com.jeanjnap.bankapp.ui.statements

import com.jeanjnap.bankapp.ui.ViewModelBaseTest
import com.jeanjnap.bankapp.util.extension.getOrAwaitValue
import com.jeanjnap.domain.entity.ErrorResponse
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.usecase.BankUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong

@ExperimentalCoroutinesApi
class StatementsViewModelTest: ViewModelBaseTest() {

    private lateinit var viewModel: StatementsViewModel

    @MockK
    private lateinit var bankUseCase: BankUseCase

    @Before
    fun setup() {
        viewModel = StatementsViewModel(network, bankUseCase)
    }

    @Test
    fun setUserId_shouldCallsGetStatements() {
        coEvery { bankUseCase.getStatements(any()) } returns mockk()

        viewModel.userId = anyLong()

        coVerify { bankUseCase.getStatements(any()) }
    }

    @Test
    fun onLogoutClick_shouldSetLogout() {
        viewModel.onLogoutClick()

        assertTrue(viewModel.onLogout.getOrAwaitValue())
    }

    @Test
    fun handleStatementsResponse_withSuccessResponse_shouldSetStatements() {
        coEvery { bankUseCase.getStatements(any()) } returns SuccessResponse(mockk())

        viewModel.userId = anyLong()

        coVerify { bankUseCase.getStatements(any()) }
        assertNotNull(viewModel.statements.getOrAwaitValue())
    }

    @Test
    fun handleStatementsResponse_withErrorResponse_shouldSetError() {
        coEvery { bankUseCase.getStatements(any()) } returns ErrorResponse(mockk())

        viewModel.userId = anyLong()

        coVerify { bankUseCase.getStatements(any()) }
        assertNotNull(viewModel.error.getOrAwaitValue())
    }
}