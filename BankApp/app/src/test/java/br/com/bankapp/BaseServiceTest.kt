package br.com.bankapp

import android.os.SystemClock
import br.com.bankapp.helpers.TestRequestDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before


open class BaseServiceTest {

    companion object {
        const val SYSTEM_ACTION_INTERVAL_TIME = 3000L
    }

    lateinit var mockWebServer: MockWebServer

    @Before
    open fun setup() {
        mockWebServer = MockWebServer().apply {
            dispatcher = TestRequestDispatcher()
            start(8080)
        }
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    fun sleep() {
        SystemClock.sleep(SYSTEM_ACTION_INTERVAL_TIME)
    }
}