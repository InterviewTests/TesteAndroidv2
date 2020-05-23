package br.com.bankapp.db.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.toLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.bankapp.data.db.BankDatabase
import br.com.bankapp.data.db.dao.StatementDao
import br.com.bankapp.data.db.entity.StatementEntity
import br.com.bankapp.data.db.entity.UserAccountEntity
import br.com.bankapp.data.utils.convertStringToDate
import br.com.fortes.appcolaborador.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class StatementDaoTest {

    private lateinit var statementDao: StatementDao
    private lateinit var bankDatabase: BankDatabase
    private lateinit var statementEntityOne: StatementEntity
    private lateinit var statementEntityTwo: StatementEntity

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        bankDatabase = Room.inMemoryDatabaseBuilder(context, BankDatabase::class.java)
            .allowMainThreadQueries() // Allowing main thread queries, just for testing.
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        statementDao = bankDatabase.statementDao()

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

    @Test
    fun insertStatements_confirmInserted() = runBlocking{
        // Add new statements
        statementDao.clearAndInsert(listOf(statementEntityOne, statementEntityTwo))

        // Get inserted statements
        val statements = statementDao.getStatements(1).toLiveData(5).getOrAwaitValue()

        // Verify that the statements have been successfully inserted
        assertThat(statements, not(emptyList<StatementEntity>()))
        assertThat(statements, IsCollectionWithSize.hasSize(2))
    }

    @Test
    fun insertStatements_confirmDeleted() = runBlocking{
        // Add new statements
        statementDao.clearAndInsert(listOf(statementEntityOne, statementEntityTwo))

        // Delete the previously inserted statements
        statementDao.delete()

        // Verify that the statements have been deleted
        val statements = statementDao.getStatements(1).toLiveData(5).getOrAwaitValue()
        assertThat(statements, `is`(emptyList<StatementEntity>()))
    }
}