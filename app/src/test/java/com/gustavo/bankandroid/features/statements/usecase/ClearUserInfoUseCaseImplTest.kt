package com.gustavo.bankandroid.features.statements.usecase

import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class ClearUserInfoUseCaseImplTest {
    private val userRepository: UserRepository = mock()

    @Test
    fun execute() {
        val clearUserInfoUseCase = ClearUserInfoUseCaseImpl(userRepository)
        clearUserInfoUseCase.execute()
        verify(userRepository).clearUsers()
    }
}