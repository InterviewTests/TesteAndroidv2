package dev.vitorpacheco.solutis.bankapp.statementsScreen

import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class StatementsInteractorTest {

    @MockK
    lateinit var worker: StatementsWorker

    @SpyK
    var output = StatementsPresenter()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `test fetchStatementsData with null userId`() {
        val interactor = StatementsInteractor()
        interactor.output = output
        interactor.worker = worker

        interactor.fetchStatementsData(StatementsRequest(null))

        verify { worker wasNot Called }

        confirmVerified(worker)
    }

    @Test
    fun `test fetchStatementsData with valid userId`() {
        val interactor = StatementsInteractor()
        interactor.output = output
        interactor.worker = worker

        interactor.fetchStatementsData(StatementsRequest(1))

        verify { worker.listStatements(StatementsRequest(1), any()) }

        interactor.fetchStatementsData(StatementsRequest(2))

        verify { worker.listStatements(StatementsRequest(2), any()) }

        confirmVerified(worker)
    }

}