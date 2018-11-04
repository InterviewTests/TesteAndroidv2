package com.ygorcesar.testeandroidv2.login

import com.ygorcesar.testeandroidv2.BaseTests
import com.ygorcesar.testeandroidv2.base.common.exception.NetworkError
import com.ygorcesar.testeandroidv2.base.common.exception.ServerError
import com.ygorcesar.testeandroidv2.login.data.LoginRepository
import com.ygorcesar.testeandroidv2.login.data.LoginService
import com.ygorcesar.testeandroidv2.login.data.UserAccountMapper
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

class LoginValidationRepositoryTests : BaseTests() {

    @Mock
    lateinit var service: LoginService

    private val mapper = UserAccountMapper()

    private lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        repository = LoginRepository.Remote(service, mapper, networkHandler)
    }

    @Test
    fun `Should authenticate with success`() {
        whenever(networkHandler.isConnected)
            .thenReturn(true)

        whenever(service.login(cpfValid, passwordValid))
            .thenReturn(Single.just(USER_RAW))

        val testObserver = repository.login(cpfValid, passwordValid).test()

        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        verify(service).login(cpfValid, passwordValid)
    }

    @Test
    fun `Should throw NetworkError on authenticate`() {
        whenever(networkHandler.isConnected)
            .thenReturn(false)

        val testObserver = repository.login(cpfValid, passwordValid).test()

        testObserver.assertNotComplete()
        testObserver.assertError(NetworkError)
    }

    @Test
    fun `Should throw ServerError on authenticate`() {
        whenever(networkHandler.isConnected)
            .thenReturn(true)

        whenever(service.login(cpfValid, passwordValid))
            .thenReturn(Single.just(USER_ERROR))

        val testObservable = repository.login(cpfValid, passwordValid).test()

        testObservable.assertNotComplete()
        testObservable.assertError(ServerError::class.java)
    }
}