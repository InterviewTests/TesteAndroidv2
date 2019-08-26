package com.valber.data.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.valber.data.*
import com.valber.data.model.mapUserAccout
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ServiceBankImplTest {

    private lateinit var service: ServiceBank
    private val bankApi: BankApi = mock()

    private val user = userMock

    @Before
    fun setUp() {
        service = ServiceBankImpl(bankApi)
    }

    @Test
    fun `get user login`() {
        whenever(bankApi.logar(user)).thenReturn(Single.just(loginResponseMock))

        val test = service.logar(user).test()

        verify(bankApi).logar(user)

        test.assertValue(loginResponseMock.userAccount.mapUserAccout())
    }

    @Test
    fun `get statements`() {
        whenever(bankApi.getStatement(1)).thenReturn(Single.just(statementResponse))
        val test = service.statement(userAccountMock.userId).test()

        verify(bankApi).getStatement(1)

        test.assertTerminated()
        test.assertComplete()
        test.assertValueCount(1)
    }
}