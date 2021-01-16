package com.jeanjnap.data.source.local

import java.lang.reflect.Type

interface Cache {
    fun <T> nullableGet(key: String, type: Type, defaultValue: T? = null): T?
    fun set(key: String, value: Any?)
}