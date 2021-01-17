package com.jeanjnap.data.repository

import com.jeanjnap.data.source.local.BankLocalDataSource
import com.jeanjnap.data.source.remote.BankRemoteDataSource
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.repository.BankRepository
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

class BankRepositoryTest {

    private lateinit var repository: BankRepository

    @MockK
    private lateinit var bankRemoteDataSource: BankRemoteDataSource

    @MockK
    private lateinit var bankLocalDataSource: BankLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = BankRepositoryImpl(bankRemoteDataSource, bankLocalDataSource)
    }

    @Test
    fun login_shouldCallsBankRemoteDataSource() {
        coEvery { bankRemoteDataSource.login(any(), any()) } returns SuccessResponse(mockk())

        runBlocking { repository.login(anyString(), anyString()) }

        coVerify { bankRemoteDataSource.login(any(), any()) }
    }

    @Test
    fun getStatements_shouldCallsRemoteDataSource() {
        coEvery { bankRemoteDataSource.getStatements(any()) } returns mockk()

        runBlocking { repository.getStatements(anyLong()) }

        coVerify { bankRemoteDataSource.getStatements(any()) }
    }

    @Test
    fun saveEncryptedUser_shouldCallsLocalDataSource() {
        every { bankLocalDataSource.saveEncryptedUser(any()) } returns Unit

        repository.saveEncryptedUser(anyString())

        verify { bankLocalDataSource.saveEncryptedUser(any()) }
    }

    @Test
    fun getUser_withSavedUser_shouldCallsReturnsEncryptedUser() {
        every { bankLocalDataSource.getEncryptedUser() } returns anyString()

        assertEquals(anyString(), repository.getUser())

        verify { bankLocalDataSource.getEncryptedUser() }
    }

    @Test
    fun getUser_withNonSavedUser_shouldNotCallsGetEncryptedUserAndDecrypt() {
        every { bankLocalDataSource.getEncryptedUser() } returns null

        assertNull(repository.getUser())

        verify { bankLocalDataSource.getEncryptedUser() }
    }
}