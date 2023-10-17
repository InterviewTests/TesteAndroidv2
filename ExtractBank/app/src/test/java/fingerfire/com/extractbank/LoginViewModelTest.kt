package fingerfire.com.extractbank

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import fingerfire.com.extractbank.features.login.data.LoginRepository
import fingerfire.com.extractbank.features.login.ui.LoginViewModel
import fingerfire.com.extractbank.features.login.ui.viewstate.LoginViewState
import fingerfire.com.extractbank.model.Login
import fingerfire.com.extractbank.model.User
import fingerfire.com.extractbank.network.ServiceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var observer: Observer<LoginViewState>

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        loginViewModel = LoginViewModel(loginRepository)
        loginViewModel.loginLiveData.observeForever(observer)
    }

    @Test
    fun loginUserSuccess() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        // Given
        val login = Login("testUser", "123456M@R")
        val user = User(1, "Test User", "12345", "6789", 1000.0)
        `when`(loginRepository.login(login)).thenReturn(ServiceState.Success(user))
        `when`(loginRepository.getSavedUser()).thenReturn(" ")

        // When
        loginViewModel.loginUser(login)

        // Then
        verify(loginRepository).login(login)
        verify(observer).onChanged(LoginViewState.Success(user))
    }

    @Test
    fun loginUserError() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val user = User(1, "Test User", "12345", "6789", 1000.0)
        // Given
        val login = Login("testUser", "123456M@R")
        val errorMessage = "Ocorreu um erro durante o login. Tente novamente mais tarde."
        `when`(loginRepository.login(login)).thenReturn(ServiceState.Error(user))

        // When
        loginViewModel.loginUser(login)

        // Then
        verify(loginRepository).login(login)
        verify(observer).onChanged(LoginViewState.Error(errorMessage))
    }
}
