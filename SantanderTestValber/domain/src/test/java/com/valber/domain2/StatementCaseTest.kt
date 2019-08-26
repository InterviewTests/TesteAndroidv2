package com.valber.domain2

import com.nhaarman.mockitokotlin2.mock
import com.valber.data.platform.NetworkHandler
import com.valber.data.remote.BankApi
import com.valber.data.repository.BankRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class StatementCaseTest {
    private val bankRepository: BankRepository = mock()
    private var networkHandler: NetworkHandler = mock()
    private lateinit var statementCase: StatementCase
    private val bankApi: BankApi = mock()

    @Before
    fun setUp() {
        statementCase = StatementCase(bankRepository, networkHandler)
    }

    @Test
    fun `no internet case statemant`() {
        val test = statementCase.getStatements(userAccountMock.userId).test()
        test.assertNotComplete()
        test.assertErrorMessage("No internet")
    }
}