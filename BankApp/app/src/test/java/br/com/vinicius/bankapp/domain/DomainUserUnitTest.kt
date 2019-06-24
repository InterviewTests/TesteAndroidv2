package br.com.vinicius.bankapp.domain

import br.com.vinicius.bankapp.domain.user.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class DomainUserUnitTest {

    @Mock
    lateinit var user: User

    lateinit var userTrue: User

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        user = mock(User::class.java)
        userTrue = User(
            "12312312312", "Test@1", "012314564",
            1000.00, "2050", "Pay", 1
        )
    }

    @Test
    fun validationMethods(){
        Mockito.`when`(user.validationPassword()).thenReturn(true)
        assertTrue(userTrue.validationPassword())
        assertFalse(userTrue.validationEmail())
        assertTrue(userTrue.validationCpf())
        assertFalse(userTrue.isValidEmpty())
    }
}