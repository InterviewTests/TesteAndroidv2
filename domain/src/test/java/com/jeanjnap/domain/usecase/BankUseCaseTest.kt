package com.jeanjnap.domain.usecase

import com.jeanjnap.domain.entity.ErrorResponse
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.repository.BankRepository
import com.jeanjnap.infrastructure.crypto.RSACrypto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString

class BankUseCaseTest {

    private lateinit var useCase: BankUseCase

    @MockK
    private lateinit var bankRepository: BankRepository

    @MockK
    private lateinit var rsaCrypto: RSACrypto

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = BankUseCaseImpl(bankRepository, rsaCrypto)
    }

    @Test
    fun login_withSuccessResponse_shouldCallsBankRepositoryAndSaveEncryptedUser() {
        coEvery { bankRepository.login(any(), any()) } returns SuccessResponse(mockk())
        every { rsaCrypto.encrypt(any()) } returns anyString()
        every { bankRepository.saveEncryptedUser(any()) } returns Unit

        runBlocking { useCase.login(anyString(), anyString()) }

        coVerify { bankRepository.login(any(), any()) }
        verify { bankRepository.saveEncryptedUser(any()) }
        verify { rsaCrypto.encrypt(any()) }
    }

    @Test
    fun login_withErrorResponse_shouldCallsBankRepositoryButNotSaveEncryptedUser() {
        coEvery { bankRepository.login(any(), any()) } returns ErrorResponse(mockk())

        runBlocking { useCase.login(anyString(), anyString()) }

        coVerify { bankRepository.login(any(), any()) }
        verify(inverse = true) { bankRepository.saveEncryptedUser(any()) }
        verify(inverse = true) { rsaCrypto.encrypt(any()) }
    }

    @Test
    fun getStatements_shouldCallsBankRepository() {
        coEvery { bankRepository.getStatements(any()) } returns mockk()

        runBlocking { useCase.getStatements(anyLong()) }

        coVerify { useCase.getStatements(any()) }
    }

    @Test
    fun getUser_withSavedUser_shouldDecryptUser() {
        every { bankRepository.getUser() } returns anyString()
        every { rsaCrypto.decrypt(any()) } returns anyString()

        assertEquals(anyString(), useCase.getUser())

        verify { bankRepository.getUser() }
        verify { rsaCrypto.decrypt(any()) }
    }

    @Test
    fun getUser_withoutSavedUser_shouldCallsRepositoryButNotDecryptUser() {
        every { bankRepository.getUser() } returns null

        assertNull(useCase.getUser())

        verify { bankRepository.getUser() }
        verify(inverse = true) { rsaCrypto.decrypt(any()) }
    }
}