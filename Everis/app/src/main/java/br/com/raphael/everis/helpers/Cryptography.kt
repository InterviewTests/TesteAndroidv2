package br.com.raphael.everis.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.math.BigInteger
import java.security.*
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.security.auth.x500.X500Principal

/*
MIT License: https://opensource.org/licenses/MIT
Copyright 2017 Diederik Hattingh
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

https://gist.github.com/Diederikjh/36ae22d5fde9d8f671a70b5d8cada90e#file-cryptography-java
*/

// Usando o algoritmo deste artigo: https://medium.com/@ericfu/securely-storing-secrets-in-an-android-application-501f030ae5a3
/*
   Convertido para Kotlin e feito um Refactor de código deprecated
 */
open class Cryptography(private val mContext: Context) {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private fun initKeys() {
        val keyStore =
            KeyStore.getInstance(ANDROID_KEY_STORE_NAME)
        keyStore.load(null)
        if (!keyStore.containsAlias(KEY_ALIAS)) {
            initValidKeys()
        } else {
            var keyValid = false
            try {
                val keyEntry =
                    keyStore.getEntry(KEY_ALIAS, null)
                if (keyEntry is KeyStore.SecretKeyEntry &&
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                ) {
                    keyValid = true
                }
                if (keyEntry is KeyStore.PrivateKeyEntry && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    val secretKey = secretKeyFromSharedPreferences
                    // When doing "Clear data" on Android 4.x it removes the shared preferences (where
                    // we have stored our encrypted secret key) but not the key entry. Check for existence
                    // of key here as well.
                    if (!TextUtils.isEmpty(secretKey)) {
                        keyValid = true
                    }
                }
            } catch (e: NullPointerException) {
                // Bad to catch null pointer exception, but looks like Android 4.4.x
                // pin switch to password Keystore bug.
                // https://issuetracker.google.com/issues/36983155
                Log.e(
                    LOG_TAG,
                    "Failed to get key store entry",
                    e
                )
            } catch (e: UnrecoverableKeyException) {
                Log.e(
                    LOG_TAG,
                    "Failed to get key store entry",
                    e
                )
            }
            if (!keyValid) {
                synchronized(s_keyInitLock) {

                    // System upgrade or something made key invalid
                    removeKeys(keyStore)
                    initValidKeys()
                }
            }
        }
    }

    @Throws(KeyStoreException::class)
    protected fun removeKeys(keyStore: KeyStore) {
        keyStore.deleteEntry(KEY_ALIAS)
        removeSavedSharedPreferences()
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private fun initValidKeys() {
        synchronized(s_keyInitLock) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                generateKeysForAPIMOrGreater()
            } else {
                generateKeysForAPILessThanM()
            }
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun removeSavedSharedPreferences() {
        val sharedPreferences = mContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        val clearedPreferencesSuccessfully =
            sharedPreferences.edit().clear().commit()
        Log.d(
            LOG_TAG,
            String.format(
                "Cleared secret key shared preferences `%s`",
                clearedPreferencesSuccessfully
            )
        )
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private fun generateKeysForAPILessThanM() {
        // Generate a key pair for encryption
        val start = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 30)
        val spec =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                KeyGenParameterSpec.Builder(KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setCertificateSubject(X500Principal("CN=$KEY_ALIAS"))
                    .setCertificateSerialNumber(BigInteger.TEN)
                    .setCertificateNotBefore(start.time)
                    .setCertificateNotAfter(end.time)
                    .build()
            } else {
                KeyPairGeneratorSpec.Builder(mContext)
                    .setAlias(KEY_ALIAS)
                    .setSubject(X500Principal("CN=$KEY_ALIAS"))
                    .setSerialNumber(BigInteger.TEN)
                    .setStartDate(start.time)
                    .setEndDate(end.time)
                    .build()
            }
        val kpg = KeyPairGenerator.getInstance(
            RSA_ALGORITHM_NAME,
            ANDROID_KEY_STORE_NAME
        )
        kpg.initialize(spec)
        kpg.generateKeyPair()
        saveEncryptedKey()
    }

    @SuppressLint("ApplySharedPref")
    private fun saveEncryptedKey() {
        val pref = mContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        var encryptedKeyBase64encoded =
            pref.getString(ENCRYPTED_KEY_NAME, null)
        if (encryptedKeyBase64encoded == null) {
            val key = ByteArray(16)
            val secureRandom = SecureRandom()
            secureRandom.nextBytes(key)
            val encryptedKey = rsaEncryptKey(key)
            encryptedKeyBase64encoded =
                Base64.encodeToString(encryptedKey, Base64.DEFAULT)
            val edit = pref.edit()
            edit.putString(ENCRYPTED_KEY_NAME, encryptedKeyBase64encoded)
            val successfullyWroteKey = edit.commit()
            if (successfullyWroteKey) {
                Log.d(LOG_TAG, "Saved keys successfully")
            } else {
                Log.e(LOG_TAG, "Saved keys unsuccessfully")
                throw IOException("Could not save keys")
            }
        }
    }

    private val secretKeyAPILessThanM: Key
        get() {
            val encryptedKeyBase64Encoded = secretKeyFromSharedPreferences
            if (TextUtils.isEmpty(encryptedKeyBase64Encoded)) {
                throw InvalidKeyException("Saved key missing from shared preferences")
            }
            val encryptedKey =
                Base64.decode(encryptedKeyBase64Encoded, Base64.DEFAULT)
            val key = rsaDecryptKey(encryptedKey)
            return SecretKeySpec(key, "AES")
        }

    private val secretKeyFromSharedPreferences: String?
        get() {
            val sharedPreferences = mContext.getSharedPreferences(
                SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
            return sharedPreferences.getString(ENCRYPTED_KEY_NAME, null)
        }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected fun generateKeysForAPIMOrGreater() {
        val keyGenerator: KeyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            ANDROID_KEY_STORE_NAME
        )
        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE) // NOTE no Random IV. According to above this is less secure but acceptably so.
                .setRandomizedEncryptionRequired(false)
                .build()
        )
        // Note according to [docs](https://developer.android.com/reference/android/security/keystore/KeyGenParameterSpec.html)
        // this generation will also add it to the keystore.
        keyGenerator.generateKey()
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun encryptData(stringDataToEncrypt: String?): String {
        initKeys()
        requireNotNull(stringDataToEncrypt) { "Data to be decrypted must be non null" }
        val cipher: Cipher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cipher = Cipher.getInstance(AES_MODE_M_OR_GREATER)
            cipher.init(
                Cipher.ENCRYPT_MODE, secretKeyAPIMorGreater,
                GCMParameterSpec(128, FIXED_IV)
            )
        } else {
            cipher = Cipher.getInstance(
                AES_MODE_LESS_THAN_M,
                CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_AES
            )
            try {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeyAPILessThanM)
            } catch (e: InvalidKeyException) {
                // Since the keys can become bad (perhaps because of lock screen change)
                // drop keys in this case.
                removeKeys()
                throw e
            } catch (e: IOException) {
                removeKeys()
                throw e
            } catch (e: IllegalArgumentException) {
                removeKeys()
                throw e
            }
        }
        val encodedBytes =
            cipher.doFinal(stringDataToEncrypt.toByteArray(charset(CHARSET_NAME)))
        return Base64.encodeToString(encodedBytes, Base64.DEFAULT)
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun decryptData(encryptedData: String?): String {
        initKeys()
        requireNotNull(encryptedData) { "Data to be decrypted must be non null" }
        val encryptedDecodedData =
            Base64.decode(encryptedData, Base64.DEFAULT)
        val c: Cipher
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                c = Cipher.getInstance(AES_MODE_M_OR_GREATER)
                c.init(
                    Cipher.DECRYPT_MODE,
                    secretKeyAPIMorGreater,
                    GCMParameterSpec(128, FIXED_IV)
                )
            } else {
                c = Cipher.getInstance(
                    AES_MODE_LESS_THAN_M,
                    CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_AES
                )
                c.init(Cipher.DECRYPT_MODE, secretKeyAPILessThanM)
            }
        } catch (e: InvalidKeyException) {
            // Since the keys can become bad (perhaps because of lock screen change)
            // drop keys in this case.
            removeKeys()
            throw e
        } catch (e: IOException) {
            removeKeys()
            throw e
        }
        val decodedBytes = c.doFinal(encryptedDecodedData)
        return String(decodedBytes, charset(CHARSET_NAME))
    }

    private val secretKeyAPIMorGreater: Key
        get() {
            val keyStore =
                KeyStore.getInstance(ANDROID_KEY_STORE_NAME)
            keyStore.load(null)
            return keyStore.getKey(KEY_ALIAS, null)
        }

    private fun rsaEncryptKey(secret: ByteArray): ByteArray {
        val keyStore =
            KeyStore.getInstance(ANDROID_KEY_STORE_NAME)
        keyStore.load(null)
        val privateKeyEntry = keyStore.getEntry(
            KEY_ALIAS,
            null
        ) as KeyStore.PrivateKeyEntry
        val inputCipher = Cipher.getInstance(
            RSA_MODE,
            CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA
        )
        inputCipher.init(
            Cipher.ENCRYPT_MODE,
            privateKeyEntry.certificate.publicKey
        )
        val outputStream = ByteArrayOutputStream()
        val cipherOutputStream =
            CipherOutputStream(outputStream, inputCipher)
        cipherOutputStream.write(secret)
        cipherOutputStream.close()
        return outputStream.toByteArray()
    }

    private fun rsaDecryptKey(encrypted: ByteArray): ByteArray {
        val keyStore =
            KeyStore.getInstance(ANDROID_KEY_STORE_NAME)
        keyStore.load(null)
        val privateKeyEntry = keyStore.getEntry(
            KEY_ALIAS,
            null
        ) as KeyStore.PrivateKeyEntry
        val output = Cipher.getInstance(
            RSA_MODE,
            CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA
        )
        output.init(Cipher.DECRYPT_MODE, privateKeyEntry.privateKey)
        val cipherInputStream = CipherInputStream(
            ByteArrayInputStream(encrypted), output
        )
        val values = ArrayList<Byte>()
        var nextByte: Int
        while (cipherInputStream.read().also { nextByte = it } != -1) {
            values.add(nextByte.toByte())
        }
        val decryptedKeyAsBytes = ByteArray(values.size)
        for (i in decryptedKeyAsBytes.indices) {
            decryptedKeyAsBytes[i] = values[i]
        }
        return decryptedKeyAsBytes
    }

    private fun removeKeys() {
        synchronized(s_keyInitLock) {
            val keyStore =
                KeyStore.getInstance(ANDROID_KEY_STORE_NAME)
            keyStore.load(null)
            removeKeys(keyStore)
        }
    }

    companion object {
        private const val ANDROID_KEY_STORE_NAME = "AndroidKeyStore"
        private const val AES_MODE_M_OR_GREATER = "AES/GCM/NoPadding"
        private const val AES_MODE_LESS_THAN_M = "AES/ECB/PKCS7Padding"
        private const val KEY_ALIAS = "UftPjrwQUGDLZWym"

        private val FIXED_IV = byteArrayOf(
            36, 36, 32, 65, 64, 65, 38, 31, 36, 39, 38, 38
        )
        private const val CHARSET_NAME = "UTF-8"
        private const val RSA_ALGORITHM_NAME = "RSA"
        private const val RSA_MODE = "RSA/ECB/PKCS1Padding"
        private const val CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA = "AndroidOpenSSL"
        private const val CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_AES = "BC"
        private const val SHARED_PREFERENCE_NAME = "iYziwCNtCBBGyDAL"
        private const val ENCRYPTED_KEY_NAME = "zsTuxaShLocRmskC"
        private val LOG_TAG = Cryptography::class.java.name
        private val s_keyInitLock = Any()
    }
}