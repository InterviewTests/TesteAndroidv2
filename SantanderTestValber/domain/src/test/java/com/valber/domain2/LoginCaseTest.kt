package com.valber.domain2

import android.content.Context
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.valber.data.model.UserAccount
import com.valber.data.platform.NetworkHandler
import com.valber.data.repository.BankRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*



class LoginCaseTest {

    private val bankRepository: BankRepository = mock()
    private var networkHandler: NetworkHandler = mock()
    private lateinit var loginCase: LoginCase
    private val user: String = userMock.user
    private val password: String = userMock.password

    @Before
    fun setUp() {

        loginCase = LoginCase(bankRepository, networkHandler)
    }

    @Test
    fun `login falid no internet `() {
        val test = loginCase.logar(user, password).test()
        test.assertNotComplete()
        test.assertErrorMessage("No internet")

    }
}