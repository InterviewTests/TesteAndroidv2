package com.example.bankapp.features.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.bankapp.features.Seeds
import com.example.bankapp.features.Seeds.MESSAGE_ERROR
import com.example.bankapp.features.Seeds.loginMock
import com.example.bankapp.features.Seeds.loginResponseErrorMock
import com.example.bankapp.features.Seeds.loginResponseMock
import com.example.bankapp.features.Seeds.userResponseMock
import com.example.bankapp.features.login.data.repository.LoginRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test

class LoginActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val schedulers = Schedulers.trampoline()
    private val repository = mock<LoginRepository>()
    private val observableScreenState = mock<Observer<ScreenState>>()
    private val observableShowLoading = mock<Observer<Boolean>>()

    private fun initializeViewModel(): LoginActivityViewModel {
        val viewModel = LoginActivityViewModel(
            mainScheduler = schedulers,
            ioScheduler = schedulers,
            repository = repository
        )
        viewModel.nameField.value = Seeds.NAME
        viewModel.passwordField.value = Seeds.PASSWORD

        viewModel.screenState.observeForever(observableScreenState)
        viewModel.showLoading.observeForever(observableShowLoading)
        return viewModel
    }

    @Test
    fun `should show error when had any request throwable`() {
        val throwable = Throwable()
        given { repository.getLogin(loginMock) }
            .willReturn(Single.error(throwable))

        initializeViewModel().onUserRequestToLogin()

        verify(observableShowLoading).onChanged(false)
        verify(observableShowLoading).onChanged(true)
        verify(observableScreenState).onChanged(ScreenState.Error())
    }

    @Test
    fun `should show a error when api response a error`(){
        given { repository.getLogin(loginMock ) }
            .willReturn(Single.just(loginResponseErrorMock))

        initializeViewModel().onUserRequestToLogin()

        verify(observableShowLoading).onChanged(false)
        verify(observableShowLoading).onChanged(true)
        verify(observableScreenState).onChanged(ScreenState.Error(MESSAGE_ERROR))
    }

    @Test
    fun `should go to details when user make log in`(){
        given { repository.getLogin(loginMock) }
            .willReturn(Single.just(loginResponseMock))

        initializeViewModel().onUserRequestToLogin()

        verify(observableShowLoading).onChanged(false)
        verify(observableShowLoading).onChanged(true)
        verify(observableScreenState).onChanged(ScreenState.Login(userResponseMock()))
    }
}