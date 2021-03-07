package br.com.silas.domain.interactor

import br.com.silas.domain.Schedulers
import br.com.silas.domain.mocks.UserMock
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.user.LoginInteractor
import br.com.silas.domain.user.LoginRepository
import br.com.silas.domain.util.TestScheduler
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginInteractorTest {
    @Mock
    lateinit var loginRepository: LoginRepository
    lateinit var testScheduler: Schedulers

    lateinit var loginInteractor: LoginInteractor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        loginInteractor = LoginInteractor(loginRepository, testScheduler)
    }

    @Test
    fun `When call to repository and fetch is successful should be return an user`() {
        val user = UserMock.getUserMock()
        val loginError = mock(ErrorResponse::class.java)

        val pairResult = Pair(user, loginError)
        `when`(loginRepository.fetchUser(anyString(), anyString())).thenReturn(Single.just(pairResult))
        val result = loginInteractor
            .execute(loginInteractor.Request("Teste", "1234"))
            .test()

        result
            .assertComplete()
            .assertNoErrors()
            .assertValue(pairResult)

        verify(loginRepository).fetchUser("Teste", "1234")
    }

    @Test
    fun `When call to repository and fetch is fail should be return an user`() {
        val exception = Exception()
        `when`(loginRepository.fetchUser(anyString(), anyString())).thenReturn(
            Single.error(
                exception
            )
        )
        val result = loginInteractor
            .execute(loginInteractor.Request("Teste", "1234"))
            .test()

        result
            .assertNotComplete()
            .assertNoValues()
            .assertError(exception)

        verify(loginRepository).fetchUser("Teste", "1234")
    }
}