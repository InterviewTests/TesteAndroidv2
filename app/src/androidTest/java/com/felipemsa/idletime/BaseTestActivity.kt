package com.felipemsa.idletime

import android.support.v7.app.AppCompatActivity
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
open class BaseTestActivity {

    fun doWait() {
        doWait(500)
    }

    fun doWait(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: Exception) {
            //do nothing
        }
    }

}