package com.jeanjnap.data.source.local

import com.jeanjnap.data.RobolectricBaseTest
import com.jeanjnap.data.util.moshi.InternalMoshiImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.Date

class CacheTest: RobolectricBaseTest() {

    private lateinit var cache: Cache

    @Before
    fun setup() {
        cache = CacheImpl(context, InternalMoshiImpl())
    }

    @Test
    fun set_shouldSaveValueInSharedPreferencesAndRetrieveWithNullableGet() {
        cache.set(KEY, VALUE)

        assertEquals(VALUE, cache.nullableGet(KEY, String::class.java))
    }

    @Test
    fun nullableGet_withNonSavedKey_shouldReturnsNull() {
        assertNull(cache.nullableGet(NON_SAVED_KEY, String::class.java))
    }

    @Test
    fun nullableGet_withDifferentType_shouldReturnsNull() {
        cache.set(KEY, VALUE)

        assertNull(cache.nullableGet(NON_SAVED_KEY, Date::class.java))
    }

    @Test
    fun nullableGet_withJsonDataException_shouldReturnsNull() {
        cache.set(KEY, anyString())

        assertNull(cache.nullableGet(KEY, Sample::class.java))
    }

    data class Sample(val test: String)

    companion object {
        private const val KEY = "key"
        private const val NON_SAVED_KEY = "nonSavedKey"
        private const val VALUE = "value"
    }
}