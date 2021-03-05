package br.com.silas.data

import br.com.silas.data.remote.api.TesteAndroidV2Service
import br.com.silas.data.remote.login.LoginRepositoryImpl
import br.com.silas.data.remote.login.LoginResponse
import br.com.silas.data.remote.login.UserMapper
import br.com.silas.domain.preferences.PreferencesRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryImplTest {
    @Mock
    lateinit var testeAndroidV2Service: TesteAndroidV2Service

    @Mock
    lateinit var preferencesRepository: PreferencesRepository

    @Mock
    lateinit var userMapper: UserMapper

    lateinit var loginRepositoryImpl: LoginRepositoryImpl

    @Before
    fun setUp() {
        loginRepositoryImpl = LoginRepositoryImpl(testeAndroidV2Service, userMapper, preferencesRepository)
    }

    @Test
    fun `Should return an user from api when login is successful`() {
        val loginResponse = mock(LoginResponse::class.java)
        `when`(testeAndroidV2Service.fetchUser(anyString(), anyString())).thenReturn(Single.just(loginResponse))
        val result = testeAndroidV2Service.fetchUser("test_user", "Test@1").test()

        result
            .assertComplete()
            .assertNoErrors()
            .assertValue(loginResponse)
    }

    @Test
    fun `Should return an exception from api when login is unsuccessful`() {
        val exception = Exception()
        `when`(testeAndroidV2Service.fetchUser(anyString(), anyString())).thenReturn(Single.error(exception))
        val result = testeAndroidV2Service.fetchUser("test_user", "Test@1").test()

        result
            .assertNotComplete()
            .assertNoValues()
            .assertError(exception)
    }
}