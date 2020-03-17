package dev.vitorpacheco.solutis.bankapp.workers

import android.content.Context
import android.content.SharedPreferences
import dev.vitorpacheco.solutis.bankapp.BankApp
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SharedPreferencesWorkerTest {

    @MockK
    lateinit var sharedPrefs: SharedPreferences

    @MockK
    lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every {
            context.getSharedPreferences(
                BankApp.SHARED_PREFERENCES_KEY,
                Context.MODE_PRIVATE
            )
        } returns sharedPrefs
    }

    @Test
    fun `tests if the worker get the value in shared preferences`() {
        every { sharedPrefs.getString("test", any()) } returns "test_value"

        val worker = SharedPreferencesWorker()
        val value = worker.get(SharedPreferencesRequest(context, "test"))

        assertEquals("test_value", value)

        verify(exactly = 1) {
            context.getSharedPreferences(
                BankApp.SHARED_PREFERENCES_KEY,
                Context.MODE_PRIVATE
            )
        }

        verify(exactly = 1) {
            sharedPrefs.getString("test", any())
        }

        confirmVerified(sharedPrefs, context)
    }

    @Test
    fun `tests if the worker stores the value in shared preferences`() {
        val editor = mockk<SharedPreferences.Editor>()
        every { sharedPrefs.edit() } returns editor

        every { editor.putString(any(), any()) } returns editor
        every { editor.apply() } just Runs

        val worker = SharedPreferencesWorker()
        worker.save(SharedPreferencesRequest(context, "test", "test_value"))

        verify(exactly = 1) {
            context.getSharedPreferences(
                BankApp.SHARED_PREFERENCES_KEY,
                Context.MODE_PRIVATE
            )
        }

        verify(exactly = 1) {
            sharedPrefs.edit()
        }

        confirmVerified(sharedPrefs, context)
    }

}