package br.com.silas.data.repositories

import br.com.silas.data.remote.api.ServiceTesteAndroidV2
import br.com.silas.data.remote.login.LoginRepositoryImpl
import br.com.silas.data.remote.login.LoginResponse
import br.com.silas.data.remote.login.UserMapper
import br.com.silas.domain.ErrorResponse
import br.com.silas.domain.user.User
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryImplTest {
    @Mock
    lateinit var testeAndroidV2Service: ServiceTesteAndroidV2

    @Mock
    lateinit var userMapper: UserMapper

    lateinit var loginRepositoryImpl: LoginRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginRepositoryImpl =
            LoginRepositoryImpl(testeAndroidV2Service, userMapper)
    }

    @Test
    fun `Should return an user from api when login is successful`() {
        val loginResponse = mock(LoginResponse::class.java)
        val user = mock(User::class.java)
        val errorResponse = mock(ErrorResponse::class.java)

        val pairResponse = Pair(user, errorResponse)


        `when`(testeAndroidV2Service.fetchUser(anyString(), anyString())).thenReturn(
            Single.just(
                loginResponse
            )
        )

        `when`(userMapper.mapperUserAccountResponseToUser(loginResponse)).thenReturn(pairResponse)

        val result = loginRepositoryImpl.fetchUser("test_user", "Test@1").test()

        result
            .assertComplete()
            .assertNoErrors()
            .assertValue(pairResponse)

        verify(testeAndroidV2Service).fetchUser("test_user", "Test@1")
    }

    @Test
    fun `Should return an exception from api when login is unsuccessful`() {
        val exception = Exception()
        `when`(testeAndroidV2Service.fetchUser(anyString(), anyString())).thenReturn(
            Single.error(
                exception
            )
        )
        val result = loginRepositoryImpl.fetchUser("test_user", "Test@1").test()

        result
            .assertNotComplete()
            .assertNoValues()
            .assertError(exception)

        verify(testeAndroidV2Service).fetchUser("test_user", "Test@1")
    }
}