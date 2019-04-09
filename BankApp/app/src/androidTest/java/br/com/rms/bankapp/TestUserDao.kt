package br.com.rms.bankapp

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import br.com.rms.bankapp.data.local.database.AppDatabase
import br.com.rms.bankapp.data.local.database.dao.UserDao
import br.com.rms.bankapp.data.local.database.entity.User
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestUserDao {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao
    val userEmail = User(0, "teste@email.com", "@Teste1", 1000)
    val userCpf = User(0, "40182994007", "@Teste1", 1000)

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java
        ).build()

        userDao = appDatabase.userDao()

    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun testInsertAndRetrievedUserMatch(){
        userDao.insertUser(userEmail)
        val dbUser = userDao.selectUser()
        assertEquals(userEmail,dbUser)
    }

    @Test
    fun testConflictingInsertsReplaceUser(){
        userDao.insertUser(userEmail)
        userDao.insertUser(userCpf)
        val expectedUser = userDao.selectUser()
        assertEquals(userCpf, expectedUser)
    }


}