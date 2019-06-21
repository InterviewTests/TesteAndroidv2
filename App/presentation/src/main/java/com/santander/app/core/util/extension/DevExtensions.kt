package com.santander.app.core.util.extension

import com.santander.app.BuildConfig

inline fun runWhenDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}