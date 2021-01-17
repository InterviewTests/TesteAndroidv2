package com.jeanjnap.data.repository

import com.jeanjnap.data.source.local.BankLocalDataSource
import com.jeanjnap.data.source.remote.BankRemoteDataSource
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
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString

class BankRepositoryTest {

    private lateinit var repository: BankRepository

    @MockK
    private lateinit var bankRemoteDataSource: BankRemoteDataSource

    @MockK
    private lateinit var bankLocalDataSource: BankLocalDataSource

    @MockK
    private lateinit var rsaCrypto: RSACrypto

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = BankRepositoryImpl(bankRemoteDataSource, bankLocalDataSource, rsaCrypto)
    }

    @Test
    fun login_withSuccessResponse_shouldCallsSaveEncryptedUser() {
        coEvery { bankRemoteDataSource.login(any(), any()) } returns SuccessResponse(mockk())
        every { rsaCrypto.encrypt(any()) } returns anyString()
        every { bankLocalDataSource.saveEncryptedUser(any()) } returns Unit

        runBlocking { repository.login(anyString(), anyString()) }

        coVerify { bankRemoteDataSource.login(any(), any()) }
        verify { rsaCrypto.encrypt(any()) }
        verify { bankLocalDataSource.saveEncryptedUser(any()) }
    }

    @Test
    fun login_withErrorResponse_shouldNotCallsSaveEncryptedUser() {
        coEvery { bankRemoteDataSource.login(any(), any()) } returns ErrorResponse(mockk())

        runBlocking { repository.login(anyString(), anyString()) }

        coVerify { bankRemoteDataSource.login(any(), any()) }
        verify(inverse = true) { rsaCrypto.encrypt(any()) }
        verify(inverse = true) { bankLocalDataSource.saveEncryptedUser(any()) }
    }

    @Test
    fun getStatements_shouldCallsRemoteDataSource() {
        coEvery { bankRemoteDataSource.getStatements(any()) } returns mockk()

        runBlocking { repository.getStatements(anyLong()) }

        coVerify { bankRemoteDataSource.getStatements(any()) }
    }

    @Test
    fun getUser_withSavedUser_shouldCallsGetEncryptedUserAndDecrypt() {
        every { bankLocalDataSource.getEncryptedUser() } returns anyString()
        every { rsaCrypto.decrypt(any()) } returns anyString()

        repository.getUser()

        verify { bankLocalDataSource.getEncryptedUser() }
        verify { rsaCrypto.decrypt(any()) }
    }

    @Test
    fun getUser_withNonSavedUser_shouldNotCallsGetEncryptedUserAndDecrypt() {
        every { bankLocalDataSource.getEncryptedUser() } returns null

        repository.getUser()

        verify { bankLocalDataSource.getEncryptedUser() }
        verify(inverse = true) { rsaCrypto.decrypt(any()) }
    }
}