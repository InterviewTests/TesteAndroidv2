package br.com.bankapp.db.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.bankapp.data.db.BankDatabase
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.db.entity.UserAccountEntity
import br.com.fortes.appcolaborador.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class UserAccountDaoTest {

    private lateinit var userAccountDao: UserAccountDao
    private lateinit var bankDatabase: BankDatabase
    private lateinit var userAccountEntity: UserAccountEntity

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
        userAccountDao = bankDatabase.userAccountDao()

        userAccountEntity = UserAccountEntity(
            userId = 1,
            name = "User",
            bankAccount = "2050",
            agency = "012314564",
            balance = 3.3445
        )
    }

    @After
    fun closeDb() {
        bankDatabase.close()
    }

    @Test
    fun insertUserAccount_confirmInserted() = runBlocking{
        // Add a new user account
        userAccountDao.clearAndInsert(userAccountEntity)

        // Get inserted user account
        val userAccountFromDB = userAccountDao.getUserByIdDistinct(1).getOrAwaitValue()

        // Verify that the user account has been successfully inserted
        assertThat(userAccountEntity.userId, equalTo(userAccountFromDB.userId))
    }

    @Test
    fun insertUserAccount_confirmDeleted() = runBlocking{
        // Add a new user account
        userAccountDao.clearAndInsert(userAccountEntity)

        // Delete the previously inserted user account
        userAccountDao.delete()

        // Verify that the user account has been deleted
        val userAccountFromDB = userAccountDao.getUserByIdDistinct(1).getOrAwaitValue()
        assertThat(userAccountFromDB, `is`(nullValue()))
    }
}