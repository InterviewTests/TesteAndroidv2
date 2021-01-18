package com.jeanjnap.bankapp.util

import androidx.test.espresso.IdlingRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class EspressoCoroutinesRule : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)

        IdlingRegistry.getInstance().register(EspressoCountingIdlingResource)

        CoroutinesExecutor.instance.launchExecutor = { asyncContext, block ->
            EspressoCountingIdlingResource.increment()

            CoroutineScope(asyncContext).launch(block = block)
                .invokeOnCompletion {
                    EspressoCountingIdlingResource.decrement()
                }
        }

        CoroutinesExecutor.instance.asyncExecutor = { asyncContext, block ->
            EspressoCountingIdlingResource.increment()

            CoroutineScope(asyncContext).async(block = block).apply {
                invokeOnCompletion {
                    EspressoCountingIdlingResource.decrement()
                }
            }
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        CoroutinesExecutor.instance.launchExecutor = null
        CoroutinesExecutor.instance.asyncExecutor = null
        IdlingRegistry.getInstance().unregister(EspressoCountingIdlingResource)
    }

}
