package com.jeanjnap.bankapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jeanjnap.bankapp.di.AppModules
import com.jeanjnap.bankapp.util.EspressoCoroutinesRule
import com.jeanjnap.infrastructure.network.Network
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest

open class BaseTest : KoinTest {

    @Rule
    @JvmField
    val coroutinesRule = EspressoCoroutinesRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    protected lateinit var network: Network

    @Before
    fun initialization() {
        MockKAnnotations.init(this)
        unloadKoinModules(AppModules.viewModelModules)

        every { network.hasActiveInternetConnection() } returns true
    }
}
