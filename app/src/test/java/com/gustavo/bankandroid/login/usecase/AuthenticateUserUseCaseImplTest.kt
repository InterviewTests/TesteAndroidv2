package com.gustavo.bankandroid.login.usecase

import com.gustavo.bankandroid.entity.LoginResponse
import com.gustavo.bankandroid.login.repository.UserRepository
import com.gustavo.bankandroid.mock.MockData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthenticateUserUseCaseImplTest {

    private val userRepository: UserRepository = mock()

    @Test
    fun `execute success`() {
        val authenticateUserUseCase = AuthenticateUserUseCaseImpl(userRepository)

        val user = MockData.mockUserInfo()
        whenever(userRepository.authenticateUser(any(), any())).thenReturn(Single.just(LoginResponse.Success(user)))

        val testSingleObserver = TestObserver<LoginResponse>()

        val result =  authenticateUserUseCase.execute(user.username, user.password)
        result.subscribe(testSingleObserver)

        assertTrue( (testSingleObserver.values()[0] is LoginResponse.Success ))
    }

    @Test
    fun `execute error`() {
        val authenticateUserUseCase = AuthenticateUserUseCaseImpl(userRepository)

        val user = MockData.mockUserInfo()
        whenever(userRepository.authenticateUser(any(), any())).thenReturn(Single.just(LoginResponse.Error))

        val testSingleObserver = TestObserver<LoginResponse>()

        val result =  authenticateUserUseCase.execute(user.username, user.password)
        result.subscribe(testSingleObserver)

        assertTrue( (testSingleObserver.values()[0] is LoginResponse.Error ))
    }
}