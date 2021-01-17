package com.jeanjnap.bankapp.ui

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jeanjnap.bankapp.R
import com.jeanjnap.bankapp.di.AppComponent
import com.jeanjnap.bankapp.di.AppModules.utilsModules
import com.jeanjnap.bankapp.di.DomainModules.domainModulesItems
import com.jeanjnap.domain.boundary.ResourcesString
import com.jeanjnap.infrastructure.network.Network
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.ArgumentMatchers.anyString
import org.robolectric.annotation.Config

@RunWith(JUnit4::class)
@Config(application = Application::class)
@ExperimentalCoroutinesApi
abstract class ViewModelBaseTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    protected lateinit var context: Context

    @MockK
    protected lateinit var network: Network

    @MockK
    protected lateinit var resourcesString: ResourcesString

    @Before
    fun mockitoInit() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        startKoin {
            androidContext(context)
            modules(AppComponent.getAllModules())
            unloadModules(utilsModules, domainModulesItems)
            loadKoinModules(
                module {
                    single { resourcesString }
                }
            )
        }
        every { resourcesString.genericError } returns anyString()
        every { network.hasActiveInternetConnection() } returns true
        every { context.getString(R.string.base_url) } returns DEFAULT_URL
    }

    @After
    fun setUpAfter() {
        stopKoin()
    }

    companion object {
        private const val DEFAULT_URL = "https://localhost"
    }
}
