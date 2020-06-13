package com.qintess.santanderapp

import android.app.Application
import android.os.Build
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.qintess.santanderapp.helper.Prefs
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class PrefsTest {
    // Prefs não é null
    @Test
    fun prefs_isNotNull() {
        val ctx = getApplicationContext<Application>()
        Assert.assertNotNull(Prefs(ctx))
    }

    // putString salva valor corretamente
    @Test
    fun putString_isSavingCorrectValue() {
        val ctx = getApplicationContext<Application>()
        val prefs = Prefs(ctx)
        val last_username = "raphacmartin"

        prefs.putString(Prefs.Key.LAST_USER, last_username)
        Assert.assertEquals(prefs.getString(Prefs.Key.LAST_USER), last_username)
    }
}