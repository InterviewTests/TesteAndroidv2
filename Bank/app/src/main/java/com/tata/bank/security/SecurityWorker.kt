package com.tata.bank.security

import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.security.KeyStore
import javax.crypto.*
import javax.crypto.spec.GCMParameterSpec

@RequiresApi(Build.VERSION_CODES.M)
class SecurityWorker(val context: Context) : ISecurityWorker {

    private val ALIAS = "com.security"
    private val KEYSTORE_PROVIDER = "AndroidKeyStore"
    private val CIPHER_TRANSFORMATION = "AES/GCM/NoPadding"

    override fun encrypt(decryptedBytes: ByteArray): CipherData {

        //Get the key
        generateKey()
        val secretKey = getKey()

        //Encrypt data
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val encrypted = cipher.doFinal(decryptedBytes)
        return CipherData(encrypted, cipher.iv)
    }

    override fun decrypt(encryptedData: CipherData): ByteArray? {
        //Get the key
        val secretKey = getKey()

        //Decrypt data
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        val spec = GCMParameterSpec(128, encryptedData.iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        return cipher.doFinal(encryptedData.encrypted)
    }

    private fun getKey(): SecretKey? {
        val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)

        val key = keyStore.getEntry(ALIAS, null) as? KeyStore.SecretKeyEntry
        return key?.secretKey
    }

    private fun generateKey() {
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            KEYSTORE_PROVIDER
        )
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setRandomizedEncryptionRequired(true)
            .build()

        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
    }
}