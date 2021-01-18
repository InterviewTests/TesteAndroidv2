@file:Suppress("DEPRECATION")

package com.jeanjnap.infrastructure.crypto.key

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.KeyStore.PrivateKeyEntry
import java.security.PrivateKey
import java.security.PublicKey
import java.util.Calendar
import javax.security.auth.x500.X500Principal

class RSAKey(
    private val context: Context
) {
    private val keyStore: KeyStore? by lazy {
        KeyStore.getInstance(ANDROID_KEY_STORE).apply {
            load(null)
        }
    }

    fun initAndGenerateKeyPair() {
        if (keyStore?.containsAlias(ALIAS) == true) {
            // We will not ask to generate every single time
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            generatePairKeyAfterAndroidM()
        } else {
            generatePairKeyBeforeAndroidM()
        }
    }

    fun getPrivateKey(): PrivateKey? {
        return if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            keyStore?.getKey(ALIAS, null) as PrivateKey
        } else {
            (keyStore?.getEntry(ALIAS, null) as? PrivateKeyEntry)?.privateKey
        }
    }

    fun getPublicKey(): PublicKey? {
        val privateKeyEntry = (keyStore?.getEntry(ALIAS, null) as? PrivateKeyEntry)
        return privateKeyEntry?.certificate?.publicKey
    }

    @Suppress("DEPRECATION")
    private fun generatePairKeyBeforeAndroidM() {
        val specBuilder = KeyPairGeneratorSpec.Builder(context).run {
            setKeySize(RSA_KEY_SIZE)
            setSerialNumber(BigInteger.TEN)
            setSubject(X500Principal("CN=$ALIAS"))
            setAlias(ALIAS)
            setStartDate(Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }.time)
            setEndDate(Calendar.getInstance().apply { add(Calendar.YEAR, 2) }.time)
            build()
        }

        KeyPairGenerator.getInstance(
            RSA_ENCRYPT_MODE,
            ANDROID_KEY_STORE
        ).apply {
            initialize(specBuilder)
            generateKeyPair()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun generatePairKeyAfterAndroidM() {
        val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA,
            ANDROID_KEY_STORE
        )
        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
            ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or
                    KeyProperties.PURPOSE_DECRYPT or
                    KeyProperties.PURPOSE_SIGN or
                    KeyProperties.PURPOSE_VERIFY
        ).run {
            setDigests(KeyProperties.DIGEST_MD5, KeyProperties.DIGEST_NONE, KeyProperties.DIGEST_SHA1, KeyProperties.DIGEST_SHA224, KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA384, KeyProperties.DIGEST_SHA512)
            setKeySize(RSA_KEY_SIZE)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
            setCertificateSerialNumber(BigInteger.TEN)
            setCertificateSubject(X500Principal("CN=$ALIAS"))
            setKeyValidityStart(Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }.time)
            setKeyValidityEnd(Calendar.getInstance().apply { add(Calendar.YEAR, 2) }.time)
            build()
        }

        kpg.apply {
            initialize(parameterSpec)
            generateKeyPair()
        }
    }

    companion object {
        private const val RSA_ENCRYPT_MODE = "RSA"
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
        private const val ALIAS = "alias"

        const val RSA_KEY_SIZE = 2048
    }
}