package br.com.bankapp.source

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.toLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.bankapp.data.api.BankAppApiService
import br.com.bankapp.data.db.BankDatabase
import br.com.bankapp.data.db.dao.StatementDao
import br.com.bankapp.data.db.entity.StatementEntity
import br.com.bankapp.data.db.entity.UserAccountEntity
import br.com.bankapp.data.source.StatementDataSource
import br.com.bankapp.data.utils.convertStringToDate
import br.com.bankapp.domain.models.Statement
import br.com.bankapp.utils.CoroutineTestRule
import br.com.bankapp.utils.SampleData
import br.com.fortes.appcolaborador.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class StatementDataSourceTest {

    private lateinit var statementDataSource: StatementDataSource
    private lateinit var statementDao: StatementDao
    private lateinit var bankDatabase: BankDatabase
    private var mockkBankApiService = mockk<BankAppApiService>()
    private lateinit var statementEntityOne: StatementEntity
    private lateinit var statementEntityTwo: StatementEntity

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        bankDatabase = Room.inMemoryDatabaseBuilder(context, BankDatabase::class.java)
            .allowMainThreadQueries() // Allowing main thread queries, just for testing.
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        statementDao = bankDatabase.statementDao()
        statementDataSource = StatementDataSource(mockkBankApiService, statementDao)

        statementEntityOne = StatementEntity(
            id = 1,
            title = "Pagamento",
            description = "Conta de luz",
            date = convertStringToDate("2018-08-15"),
            value = -50.0,
            userId = 1
        )

        statementEntityTwo = StatementEntity(
            id = 2,
            title = "TED Recebida",
            description = "Joao Alfredo",
            date = convertStringToDate("2018-07-25"),
            value = -745.03,
            userId = 1
        )

        val userAccountDao = bankDatabase.userAccountDao()
        val userAccountEntity = UserAccountEntity(
            userId = 1,
            name = "User",
            bankAccount = "2050",
            agency = "012314564",
            balance = 3.3445
        )
        runBlocking {
            // Add a new user account
            userAccountDao.clearAndInsert(userAccountEntity)
        }
    }

    @After
    fun closeDb() {
        bankDatabase.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeLoadStatements_verifyStatements() = runBlocking {
        coEvery { mockkBankApiService.loadStatements(any()) } returns SampleData.statementResponse
        statementDataSource.loadStatements(1)

        val statements = statementDao.getStatements(1).toLiveData(5).getOrAwaitValue()

        MatcherAssert.assertThat(statements, CoreMatchers.not(emptyList<StatementEntity>()))
        MatcherAssert.assertThat(statements, IsCollectionWithSize.hasSize(2))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getStatements_confirmObtained() = runBlocking {
        statementDao.clearAndInsert(listOf(statementEntityOne, statementEntityTwo))

        val statements = statementDataSource.getStatements(1).getOrAwaitValue()

        MatcherAssert.assertThat(statements, CoreMatchers.not(emptyList<Statement>()))
        MatcherAssert.assertThat(statements, IsCollectionWithSize.hasSize(2))
    }
}