package br.com.rms.bankapp

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import br.com.rms.bankapp.data.local.database.AppDatabase
import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.entity.Account
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestAccountDao {

    private lateinit var appDatabase: AppDatabase
    private lateinit var accountDao: AccountDao

    val account1 = Account(0, "User A", "000111444", "741", 1986.0)
    val account2 = Account(0, "User B", "000111333", "742", 1987.0)

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java
        ).build()

        accountDao = appDatabase.accountDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun testInsertAndRetrievedAccountMatch() {
        accountDao.insertAccount(account1)
        val accounDb = accountDao.selectAccount(0)
            assertEquals(account1, accounDb)
    }

    @Test
    fun testConflictingInsertsReplaceAccount(){
        accountDao.insertAccount(account1)
        accountDao.insertAccount(account2)
        val accounDb = accountDao.selectAccount(0)
        assertEquals(account2, accounDb)
    }


}