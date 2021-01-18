package com.jeanjnap.bankapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

typealias CoroutinesLaunchExecutor = (CoroutineDispatcher, suspend CoroutineScope.() -> Unit) -> Unit

typealias CoroutinesAsyncExecutor<T> = (CoroutineDispatcher, suspend CoroutineScope.() -> T) -> Deferred<T>

class CoroutinesExecutor private constructor() {

    var launchExecutor: CoroutinesLaunchExecutor? = null

    var asyncExecutor: CoroutinesAsyncExecutor<*>? = null

    private val defaultLaunchExecutor: CoroutinesLaunchExecutor

    private val defaultAsyncExecutor: CoroutinesAsyncExecutor<*>

    init {
        defaultLaunchExecutor = { asyncContext, block ->
            CoroutineScope(asyncContext).launch(block = block)
        }

        defaultAsyncExecutor = { asyncContext, block ->
            CoroutineScope(asyncContext).async(block = block)
        }
    }

    companion object {
        var instance: CoroutinesExecutor = CoroutinesExecutor()
    }
}