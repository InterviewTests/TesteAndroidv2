package com.example.testeandroidv2.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testeandroidv2.R
import com.example.testeandroidv2.data.repository.login.LoginRepository
import com.example.testeandroidv2.data.repository.login.LoginResult
import com.example.testeandroidv2.domain.login.UserAccount
import com.example.testeandroidv2.domain.login.User
import com.example.testeandroidv2.presentation.login.viewmodel.LoginViewModel
import com.example.testeandroidv2.utilHelper.Constants
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginLiveDataObserver: Observer<UserAccount?>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>?>

    private lateinit var viewModel : LoginViewModel

    @Test
    fun `when view model login get success then sets statementsLiveData`() {

        // Arrange
        val userAccount = UserAccount(agency = "012314564", balance = 3.3445, bankAccount = "2050", name = "José da Silva", userId = 1)
        val user = User(user = "user@gmail.com", password = "Test@1")

        val resultSuccess = MockRepositoryLogin(LoginResult.Success(userAccount))
        viewModel = LoginViewModel(resultSuccess)
        viewModel.loginLiveData.observeForever(loginLiveDataObserver)
        viewModel.viewFlipperLoginLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.login(user)

        // Assert
        verify(loginLiveDataObserver).onChanged(userAccount)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(Constants.VIEW_FLIPPER_SUCCESS, null))
    }

    @Test
    fun `when view model login get api error then sets viewFlipperLiveData`() {

        // Arrange
        val user = User(user = "user@gmail.com", password = "Test@1")

        val resultApiError = MockRepositoryLogin(LoginResult.ApiError(404))
        viewModel = LoginViewModel(resultApiError)
        viewModel.viewFlipperLoginLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.login(user)

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_400))
    }


    @Test
    fun `when view model login get server error then sets viewFlipperLiveData`() {

        // Arrange
        val user = User(user = "user@gmail.com", password = "Test@1")

        val resultServerError = MockRepositoryLogin(LoginResult.ServerError)
        viewModel = LoginViewModel(resultServerError)
        viewModel.viewFlipperLoginLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.login(user)

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(Constants.VIEW_FLIPPER_ERRO, R.string.statements_error_500))
    }
}

class  MockRepositoryLogin(private val result: LoginResult) : LoginRepository {
    override fun login(user: User, loginResultCallback: (result: LoginResult) -> Unit) {
        loginResultCallback(result)
    }

}