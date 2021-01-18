package com.jeanjnap.domain.util.extensions

import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.Date

class DateExtensionsTest {

    @Test
    fun formatToString() {
        Assert.assertEquals("17/01/2021", Date(1610852400000).formatToString())
        Assert.assertNull(anyString().formatStringAsDate())
    }
}