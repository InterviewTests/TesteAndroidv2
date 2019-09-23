package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.features.login.repository.UserRepository
import com.gustavo.bankandroid.mock.MockData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class StoreUserInfoUseCaseImplTest {

    private val userRepository: UserRepository = mock()

    @Test
    fun execute() {
        val storeUserInfoUseCase = StoreUserInfoUseCaseImpl(userRepository)
        storeUserInfoUseCase.execute(MockData.mockUserInfo())
        verify(userRepository).saveUser(MockData.mockUserInfo())
    }
}