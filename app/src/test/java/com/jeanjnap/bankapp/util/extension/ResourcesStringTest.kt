package com.jeanjnap.bankapp.util.extension

import com.jeanjnap.bankapp.RobolectricBaseTest
import com.jeanjnap.bankapp.util.ResourcesStringImpl
import com.jeanjnap.domain.boundary.ResourcesString
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResourcesStringTest : RobolectricBaseTest() {

    private lateinit var resourcesString: ResourcesString

    @Before
    fun setup() {
        resourcesString = ResourcesStringImpl(context)
    }

    @Test
    fun genericError_shouldReturnsMattingText() {
        assertEquals("Ops! Algo está errado. Tente mais tarde", resourcesString.genericError)
    }

    @Test
    fun noConnectionError_shouldReturnsMattingText() {
        assertEquals("Sem conexão à Internet", resourcesString.noConnectionError)
    }
}