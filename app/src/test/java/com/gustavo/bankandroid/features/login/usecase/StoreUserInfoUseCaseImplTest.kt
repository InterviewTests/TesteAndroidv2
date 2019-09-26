package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.common.mock.MockData
import com.gustavo.bankandroid.domain.contracts.RepositoriesContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class StoreUserInfoUseCaseImplTest {

    private val userRepository: RepositoriesContract.UserRepository = mock()

    @Test
    fun execute() {
        val storeUserInfoUseCase = StoreUserInfoUseCaseImpl(userRepository)
        storeUserInfoUseCase.execute(MockData.mockUserInfo())
        verify(userRepository).saveUser(MockData.mockUserInfo())
    }
}