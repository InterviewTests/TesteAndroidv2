package com.santander.data.util.security.crypto

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class CryptoManagerTest {

    @Test
    fun encryptAndDecryptText() {

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val text = "Test cryptography"
        val alias = "alias_1"
        val cryptographyManager = CryptoManager.Builder(alias = alias, context = appContext).build()

        val encryptedStr = cryptographyManager.encrypt(plainStr = text)
        val decryptedStr = cryptographyManager.decrypt(encryptedStr = encryptedStr)

        assertEquals(text, decryptedStr)
    }
}
