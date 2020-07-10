package br.com.mdr.testeandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.mdr.testeandroid.di.*
import br.com.mdr.testeandroid.extensions.getOrAwaitValue
import br.com.mdr.testeandroid.flow.signin.*
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
 * @since 09/07/20
 */

@RunWith(JUnit4::class)
class SignInViewModelTest: KoinTest {
    private val viewModel: SignInViewModel by inject()

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
    fun `Verify if sign in request returns user`() = runBlockingTest {
        viewModel.signInHandler.signInPresenter.userName = "email@email.com"
        viewModel.signInHandler.signInPresenter.password = "M1a."
        viewModel.callSignIn()

        Assert.assertEquals(true, viewModel.userLive.getOrAwaitValue() != null)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Verify if sign in request returns error`() = runBlockingTest {
        viewModel.signInHandler.signInPresenter.userName = ""
        viewModel.signInHandler.signInPresenter.password = ""
        viewModel.callSignIn()

        Assert.assertEquals(true, viewModel.errorLive.getOrAwaitValue() != null)
    }

}