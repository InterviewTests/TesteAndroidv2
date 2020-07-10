package br.com.mdr.testeandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.mdr.testeandroid.di.*
import br.com.mdr.testeandroid.extensions.getOrAwaitValue
import br.com.mdr.testeandroid.flow.dashboard.DashboardViewModel
import br.com.mdr.testeandroid.model.business.User
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject


/**
 * @author Marlon D. Rocha
 * @since 10/07/20
 */

@RunWith(JUnit4::class)
class DashboardViewModelTest: KoinTest {
    private val viewModel: DashboardViewModel by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        startKoin {
            modules(
                listOf(
                        apiModule,
                        modelModule,
                        networkModule,
                        repositoryModule,
                        serviceModule,
                        viewModelModule,
                        presenterModule
                )
            )
        }
    }

    @After
    fun removeModules() {
        stopKoin()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Verify if statements request returns a not empty list`() = runBlockingTest {
        val user = User(1)
        viewModel.fetchUserStatements(user)

        Assert.assertEquals(true, viewModel.statementsLive.getOrAwaitValue()?.isNotEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Verify if statements request returns a empty list`() = runBlockingTest {
        val user = User(5)
        viewModel.fetchUserStatements(user)

        Assert.assertNotEquals(true, viewModel.statementsLive.getOrAwaitValue()?.isEmpty())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Verify if statements request returns not null object`() = runBlockingTest {
        val user = User(2)
        viewModel.fetchUserStatements(user)

        Assert.assertNotNull(viewModel.statementsLive.getOrAwaitValue())
    }

}