package br.com.silas.testeandroidv2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.silas.domain.user.LoginInteractor
import br.com.silas.domain.user.User
import br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.mocks.UserMock
import br.com.silas.testeandroidv2.ui.user.LoginViewModel
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
    lateinit var loginInteractor: LoginInteractor
    lateinit var userMock: User
    lateinit var userViewModel: LoginViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        userMock = UserMock.getUserMock()
        userViewModel = LoginViewModel(loginInteractor)
    }

    @Test
    @Throws(Exception::class)
    fun `when fetch user is successful should be return an user`() {
        `when`(loginInteractor.execute(any())).thenReturn(Single.just(userMock))
        userViewModel.fetUser("user", "123")

        assertThat(userViewModel.result.value, `is`(userMock))
    }

    @Test
    @Throws(Exception::class)
    fun `when fetch user is fail should be return an exception`() {
        val exception = Exception()
        `when`(loginInteractor.execute(any())).thenReturn(Single.error(exception))
        userViewModel.fetUser("user", "123")

        assertThat(userViewModel.error.value, `is`(exception))
    }
}