package com.example.desafiosantander.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafiosantander.di.BASE_URL
import com.example.desafiosantander.di.networkModule
import com.example.desafiosantander.di.repositoryModule
import com.example.desafiosantander.di.viewModelModule
import com.example.desafiosantander.rules.RxImmediateSchedulerRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

open class BaseViewModelTest : KoinTest {

    private var mockWebServer = MockWebServer()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    var rxRule = RxImmediateSchedulerRule()

    @Before
    @Throws
    fun setUp() {
        mockWebServer.start()

        startKoin {
            modules(getModule())

            properties(mapOf(BASE_URL to mockWebServer.url("/").toString()))
        }
    }

    @After
    @Throws
    fun after() {
        stopKoin()
        mockWebServer.shutdown()
    }

    private fun getModule() = listOf(networkModule, repositoryModule, viewModelModule)

    fun mockResponse(mockJson: String) {
        mockWebServer.enqueue(MockResponse().setBody(mockJson))
    }
}