package com.jeanjnap.bankapp.util

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

object EspressoCountingIdlingResource : IdlingResource {

    private val counter = AtomicInteger(0)

    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return EspressoCountingIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        EspressoCountingIdlingResource.resourceCallback = resourceCallback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        with(counter.decrementAndGet()) {
            if (this == 0) {
                resourceCallback?.onTransitionToIdle()
            }

            if (this < 0) {
                throw IllegalArgumentException("O contador foi corrompido")
            }
        }
    }
}
