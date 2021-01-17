package com.jeanjnap.data.source.local

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class BankLocalDataSourceTest {

    private lateinit var dataSource: BankLocalDataSource

    @MockK
    private lateinit var cache: Cache

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        dataSource = BankLocalDataSourceImpl(cache)
    }

    @Test
    fun saveEncryptedUser_shouldCallsCash() {
        every { cache.set(any(), any()) } returns Unit

        dataSource.saveEncryptedUser(anyString())

        verify { cache.set(any(), any()) }
    }

    @Test
    fun getEncryptedUser_withUserSaved_shouldReturnsEncryptedUser() {
        every { cache.nullableGet<String>(any(), any()) } returns anyString()

        assertEquals(anyString(), dataSource.getEncryptedUser())
        verify { cache.nullableGet<String>(any(), any()) }
    }

    @Test
    fun getEncryptedUser_withoutUserSaved_shouldReturnsNull() {
        every { cache.nullableGet<String>(any(), any()) } returns null

        assertNull(dataSource.getEncryptedUser())
        verify { cache.nullableGet<String>(any(), any()) }
    }
}