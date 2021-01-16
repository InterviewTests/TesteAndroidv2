package com.jeanjnap.data.source.local

import android.content.Context
import com.jeanjnap.data.util.moshi.InternalMoshi
import java.io.EOFException
import java.lang.reflect.Type

class CacheImpl(
    context: Context,
    moshi: InternalMoshi
): Cache {
    private val prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    private val moshi = moshi.getMoshi()

    override fun <T> nullableGet(key: String, type: Type, defaultValue: T?): T? {
        return prefs.getString(key, null)?.let {
            try {
                moshi.adapter<T>(type).fromJson(it)
            } catch (e: EOFException) {
                defaultValue
            }
        } ?: defaultValue
    }

    override fun set(key: String, value: Any?) {
        with(prefs.edit()) {
            if (value == null) remove(key)
            else putString(key, moshi.adapter(Any::class.java).toJson(value))
            apply()
        }
    }

    companion object {
        private const val APP_PREFERENCES = "APP_PREFERENCES"
    }
}