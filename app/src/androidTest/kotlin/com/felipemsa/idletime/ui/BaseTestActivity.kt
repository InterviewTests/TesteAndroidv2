package com.felipemsa.idletime.ui

import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

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