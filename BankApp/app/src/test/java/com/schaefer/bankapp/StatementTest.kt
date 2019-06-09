package com.schaefer.bankapp

import android.content.Context
import com.schaefer.bankapp.login_screen.interactor.CurrencyInteractorImp
import com.schaefer.bankapp.login_screen.interactor.CurrencyInteractorInput
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.statement.StatementResult
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito


class StatementTest : CurrencyInteractorInput.FinishedListener {
    @Test
    fun getListStatementTest() {
        val interactor = CurrencyInteractorImp()
        interactor.getListStatement(1, Mockito.mock(Context::class.java), this)
    }

    @Test
    fun logoutUserTest() {
        val interactor = CurrencyInteractorImp()
        interactor.logout(Mockito.mock(Context::class.java), this)
    }

    override fun successGetListStatement(result: StatementResult) {
        assertTrue(true)
    }

    override fun errorGetListStatement(errorResult: ErrorResult) {
    }

    override fun genericError(message: String) {
    }

    override fun successLogout() {
        assertTrue(true)
    }

    override fun errorLogout() {
    }
}