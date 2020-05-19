package br.com.bankapp

import android.os.SystemClock
import okhttp3.mockwebserver.MockWebServer
import org.junit.After


open class BaseTest {

    companion object {
        const val SYSTEM_ACTION_INTERVAL_TIME = 3000L
    }

    lateinit var mockWebServer: MockWebServer

    @After
    open fun tearDown() {
        if (this::mockWebServer.isInitialized) {
            mockWebServer.shutdown()
        }
    }

    fun sleep() {
        SystemClock.sleep(SYSTEM_ACTION_INTERVAL_TIME)
    }
}