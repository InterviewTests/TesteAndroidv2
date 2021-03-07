package br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.silas.domain.user.GetUserInteractor
import br.com.silas.domain.user.LoginInteractor
import br.com.silas.domain.user.SaveUserInteractor
import br.com.silas.domain.user.User
import br.com.silas.testeandroidv2.any
import br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.mocks.ErrorResponseMock
import br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.mocks.UserMock
import br.com.silas.testeandroidv2.ui.user.LoginViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginViewModelTest {
    @Mock
    private lateinit var loginInteractor: LoginInteractor
    @Mock
    private lateinit var saveUserInteractor: SaveUserInteractor

    @Mock
    lateinit var getUserInteractor: GetUserInteractor

    lateinit var userMock: User
    lateinit var loginViewModel: LoginViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        userMock = UserMock.getUserMock()
        loginViewModel = LoginViewModel(loginInteractor, getUserInteractor, saveUserInteractor)
    }

    @Test
    @Throws(Exception::class)
    fun `when fetch user is successful should be return an user`() {
        val loginError = ErrorResponseMock.getErrorResponseIsEmpty()
        val pairResult = Pair(userMock, loginError)

        `when`(loginInteractor.execute(any())).thenReturn(Single.just(pairResult))
        loginViewModel.fetUser("teste@teste.com", "Teste#01")

        assertThat(loginViewModel.user.value, `is`(pairResult.first))
    }

    @Test
    @Throws(Exception::class)
    fun `when fetch user is unsuccessful should be return a message of error`() {
        val loginError = ErrorResponseMock.getErrorResponseIsNotEmpty()
        val pairResult = Pair(userMock, loginError)

        `when`(loginInteractor.execute(any())).thenReturn(Single.just(pairResult))
        loginViewModel.fetUser("teste@teste.com", "Teste#01")

        assertThat(loginViewModel.errorLogin.value, `is`(pairResult.second))
    }

    @Test
    @Throws(Exception::class)
    fun `when fetch user is fail should be return an exception`() {
        val exception = Exception()
        `when`(loginInteractor.execute(any())).thenReturn(Single.error(exception))
        loginViewModel.fetUser("user", "123")

        assertThat(loginViewModel.error.value, `is`(exception))
    }

    @Test
    fun `When fetch for user logged, should be return an user`() {
        val user = UserMock.getUserMock()
        `when`(getUserInteractor.execute()).thenReturn(user)
        loginViewModel.getLastLoggedUser()

        assertThat(loginViewModel.lastUserLogged.value, `is`(user))
    }

    @Test
    fun `When save user in preferences is success should be return true`() {
        val user = UserMock.getUserMock()
        `when`(saveUserInteractor.execute(any())).thenReturn(Completable.complete())
        loginViewModel.saveUser(user)

        assertThat(loginViewModel.userSaved.value, `is`(true))
    }

    @Test
    fun `When save user in preferences is unsuccessful should be return an exception`() {
        val user = UserMock.getUserMock()
        val exception = Exception()
        `when`(saveUserInteractor.execute(any())).thenReturn(Completable.error(exception))
        loginViewModel.saveUser(user)

        assertThat(loginViewModel.userSaved.value, `is`(false))
    }
}