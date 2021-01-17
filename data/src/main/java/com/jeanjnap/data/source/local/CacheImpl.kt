package com.jeanjnap.data.source.local

import android.content.Context
import com.jeanjnap.data.util.moshi.InternalMoshi
import com.squareup.moshi.JsonDataException
import java.lang.reflect.Type

class CacheImpl(
    context: Context,
    private val moshi: InternalMoshi
): Cache {
    private val prefs = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    override fun <T> nullableGet(key: String, type: Type, defaultValue: T?): T? {
        return prefs.getString(key, null)?.let {
            try {
                moshi.getMoshi().adapter<T>(type).fromJson(it)
            } catch (e: JsonDataException) {
                defaultValue
            }
        } ?: defaultValue
    }

    override fun set(key: String, value: Any?) {
        with(prefs.edit()) {
            if (value == null) remove(key)
            else putString(key, moshi.getMoshi().adapter(Any::class.java).toJson(value))
            apply()
        }
    }

    companion object {
        private const val APP_PREFERENCES = "APP_PREFERENCES"
    }
}