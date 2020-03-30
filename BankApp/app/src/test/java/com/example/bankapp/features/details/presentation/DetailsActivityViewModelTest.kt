package com.example.bankapp.features.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.bankapp.features.Seeds.USER_ID
import com.example.bankapp.features.Seeds.emptyStatements
import com.example.bankapp.features.Seeds.statements
import com.example.bankapp.features.Seeds.userResponseMock
import com.example.bankapp.features.details.data.repository.DetailsRepository
import com.example.bankapp.features.details.presentantion.DetailsActivityViewModel
import com.example.bankapp.features.details.presentantion.ScreenState
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test

class DetailsActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val schedulers = Schedulers.trampoline()
    private val repository = mock<DetailsRepository>()
    private val observableScreenState = mock<Observer<ScreenState>>()
    private val observableShowLoading = mock<Observer<Boolean>>()
    private val observableShowLabel = mock<Observer<Boolean>>()


    private fun initializeViewMode(): DetailsActivityViewModel {
        val viewModel = DetailsActivityViewModel(
            mainScheduler = schedulers,
            ioScheduler = schedulers,
            repository = repository,
            userAccount = userResponseMock()
        )

        viewModel.screenState.observeForever(observableScreenState)
        viewModel.showLoading.observeForever(observableShowLoading)
        viewModel.showNewsLabel.observeForever(observableShowLabel)

        return viewModel
    }


    @Test
    fun `should be show details when has details`() {
        given { repository.getStatementDetails(USER_ID) }
            .willReturn(Single.just(statements))

        initializeViewMode().fetchStatements()


        verify(observableScreenState)
            .onChanged(ScreenState.Statements(statements = statements.statementList))
        verify(observableShowLabel).onChanged(true)
        verify(observableShowLoading).onChanged(true)
        verify(observableShowLoading).onChanged(false)
    }

    @Test
    fun `should be not show news label when statement list are empty`() {
        given { repository.getStatementDetails(USER_ID) }
            .willReturn(Single.just(emptyStatements))

        initializeViewMode().fetchStatements()

        verify(observableShowLoading).onChanged(true)
        verify(observableShowLoading).onChanged(false)
        verify(observableShowLabel).onChanged(false)
    }

    @Test
    fun `should be show retry dialog when request return a error`() {
        given { repository.getStatementDetails(USER_ID) }
            .willReturn(Single.error(Throwable()))

        initializeViewMode().fetchStatements()

        verify(observableScreenState).onChanged(ScreenState.Error)
    }

    @Test
    fun `should be show logoff dialog when user request to exit`() {
        initializeViewMode().onRequestToLogoff()

        verify(observableScreenState).onChanged(ScreenState.Logoff)
    }
}