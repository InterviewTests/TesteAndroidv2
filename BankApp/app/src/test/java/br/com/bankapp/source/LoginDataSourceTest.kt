package br.com.bankapp.source

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.bankapp.data.api.BankAppApiService
import br.com.bankapp.data.db.BankDatabase
import br.com.bankapp.data.db.dao.UserAccountDao
import br.com.bankapp.data.source.LoginDataSource
import br.com.bankapp.data.utils.SharedPrefsHelper
import br.com.bankapp.utils.CoroutineTestRule
import br.com.bankapp.utils.SampleData
import br.com.fortes.appcolaborador.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class LoginDataSourceTest {

    private lateinit var loginDataSource: LoginDataSource
    private lateinit var userAccountDao: UserAccountDao
    private lateinit var bankDatabase: BankDatabase
    private lateinit var sharedPrefsHelper: SharedPrefsHelper
    private var mockkBankApiService = mockk<BankAppApiService>()

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
        userAccountDao = bankDatabase.userAccountDao()
        val sharedPreferences = context.getSharedPreferences(
            SharedPrefsHelper.PREF_USER,
            Context.MODE_PRIVATE
        );
        sharedPrefsHelper = SharedPrefsHelper(sharedPreferences)
        loginDataSource = LoginDataSource(mockkBankApiService, userAccountDao, sharedPrefsHelper)
    }

    @After
    fun closeDb() {
        bankDatabase.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun executeAttemptLogin_verifyBdAndPreferences() = runBlocking {
        coEvery { mockkBankApiService.login(any(), any()) } returns SampleData.loginResponse
        loginDataSource.attemptLogin("user", "password")

        val userAccount = userAccountDao.getUserByIdDistinct(1).getOrAwaitValue()
        assertThat(userAccount, notNullValue())

        assertThat(sharedPrefsHelper.hasKey(SharedPrefsHelper.PREF_USER), `is`(true))
    }
}