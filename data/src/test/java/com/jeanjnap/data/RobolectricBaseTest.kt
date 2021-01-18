package com.jeanjnap.data

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@Suppress("DEPRECATION")
@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, sdk = [Build.VERSION_CODES.P])
@LooperMode(LooperMode.Mode.LEGACY)
abstract class RobolectricBaseTest {

    lateinit var context: Context
    lateinit var application: Application

    @Before
    fun initializeApplication() {
        MockKAnnotations.init(this)
        context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        application = InstrumentationRegistry.getInstrumentation().targetContext as Application
    }
}