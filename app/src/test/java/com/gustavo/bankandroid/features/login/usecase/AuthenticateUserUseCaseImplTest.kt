package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.common.mock.MockData
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.gustavo.bankandroid.entity.UserLoginResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthenticateUserUseCaseImplTest {

    private val userRepository: RepositoriesContract.UserRepository = mock()

    @Test
    fun `execute success`() {
        val authenticateUserUseCase = AuthenticateUserUseCaseImpl(userRepository)

        val user = MockData.mockUserInfo()
        whenever(userRepository.authenticateUser(any(), any())).thenReturn(Single.just(UserLoginResponse.Success(user)))

        val testSingleObserver = TestObserver<UserLoginResponse>()

        val result =  authenticateUserUseCase.execute("test", "1")
        result.subscribe(testSingleObserver)

        assertTrue( (testSingleObserver.values()[0] is UserLoginResponse.Success ))
    }

    @Test
    fun `execute error`() {
        val authenticateUserUseCase = AuthenticateUserUseCaseImpl(userRepository)

        val user = MockData.mockUserInfo()
        whenever(userRepository.authenticateUser(any(), any())).thenReturn(Single.just(UserLoginResponse.Error))

        val testSingleObserver = TestObserver<UserLoginResponse>()

        val result =  authenticateUserUseCase.execute("test", "1")
        result.subscribe(testSingleObserver)

        assertTrue( (testSingleObserver.values()[0] is UserLoginResponse.Error ))
    }
}