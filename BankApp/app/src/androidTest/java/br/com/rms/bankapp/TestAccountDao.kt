package br.com.rms.bankapp

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import br.com.rms.bankapp.data.local.database.AppDatabase
import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.entity.Account
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestAccountDao {

    private lateinit var appDatabase: AppDatabase
    private lateinit var accountDao: AccountDao

    val account = Account(0,"Teste do Teste","000111444","741", 1986.0)

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
    fun testInsertUser() {
        accountDao.insertAccount(account)
        val byName = accountDao.selectAccount(accountId = account.userId)
        if(byName.name.equals(account.name)){
            assert(true)
        }else {
            error(false)
        }
    }



}