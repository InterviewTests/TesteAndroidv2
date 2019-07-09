package br.com.learncleanarchitecture.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import br.com.learncleanarchitecture.login.data.room.CleanCodeRoomDatabase
import br.com.learncleanarchitecture.login.data.room.LoginCrudDAO
import br.com.learncleanarchitecture.login.data.room.LoginEntity
import br.com.learncleanarchitecture.util.CryptoFakeUtil
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginDaoTest {

    private lateinit var cleanCodeRoomDatabase: CleanCodeRoomDatabase
    private lateinit var loginDao: LoginCrudDAO

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        cleanCodeRoomDatabase = Room.inMemoryDatabaseBuilder(context, CleanCodeRoomDatabase::class.java).allowMainThreadQueries().build()
        loginDao = cleanCodeRoomDatabase.getLoginDao()

        cleanCodeRoomDatabase.getLoginDao().insert(
                             LoginEntity(userId = 1,
                                         name = "Jose da Silva Teste",
                                         bankAccount = "2050",
                                            agency = "012314564", balance = 3.3445F, username = "jose@email.com", pass = CryptoFakeUtil.encode("Tip03#")))
    }

    @After
    fun closeDb() {
        cleanCodeRoomDatabase.close()
    }

    @Test
    fun testNotNullLogin() {
        Assert.assertNotNull(loginDao.getAllLogin())
    }

    /**
     *
     * E assim vai para os demais testes do db..
     *
     */
}