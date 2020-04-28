package br.com.raphael.everis.viewmodel

import br.com.raphael.everis.BaseTest
import br.com.raphael.everis.extensions.format
import br.com.raphael.everis.extensions.formatServer
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class StatementsViewModelTest : BaseTest() {

    lateinit var statementsViewModel: StatementsViewModel

    @Before
    override fun setUp() {
        super.setUp()

        statementsViewModel = StatementsViewModel(app)
    }

    @Test
    fun `Testar os dados do statements`() = runBlocking {

        statementsViewModel.getStatements(any())

        statementsViewModel.success.observeForever {
            // empty
            assertTrue(it.isNotEmpty())

            // size
            assertEquals(9, it.size)

            // index 0
            assertEquals("Pagamento", it[0].title)
            assertEquals("Conta de luz", it[0].desc)
            assertEquals("2018-08-15", it[0].date.formatServer())
            assertEquals(-50.0, it[0].value, 0.0)
        }
    }
}
