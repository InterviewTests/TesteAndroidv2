package br.com.teste.santander

import android.content.Context
import br.com.teste.santander.spy.StatementsRepositorySpy
import br.com.teste.santander.spy.StatementsViewSpy
import br.com.teste.santander.statements.interactor.StatementsInteractorImpl
import br.com.teste.santander.statements.presenter.StatementsPresenterImpl
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import java.lang.ref.WeakReference

class StatementsTest {

    @Test
    fun getStatementsPresenterSuccess() {
        val view = StatementsViewSpy()

        val presenter = StatementsPresenterImpl()
        presenter.statementsView = WeakReference(view)

        presenter.onRequestStatementsSuccess(ArrayList())

        assertTrue(view.onRequestStatementsSuccessCalled)
        assertFalse(view.onRequestStatementsError)
    }

    @Test
    fun getStatementsPresenterError() {
        val view = StatementsViewSpy()

        val presenter = StatementsPresenterImpl()
        presenter.statementsView = WeakReference(view)

        presenter.onRequestStatementsError("")

        assertTrue(view.onRequestStatementsError)
        assertFalse(view.onRequestStatementsSuccessCalled)
    }

    @Test
    fun getStatementsRepositoryTest() {
        val repository = StatementsRepositorySpy()

        val interactor = StatementsInteractorImpl()
        interactor.repository = repository

        interactor.getStatements(Mockito.mock(Context::class.java), 1)

        assertTrue(repository.getStatementsCalled)
    }
}