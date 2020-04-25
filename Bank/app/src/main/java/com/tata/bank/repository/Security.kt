package com.tata.bank.repository

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object Security {

    private const val ALIAS = "com.tata.security"
    private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
    private const val CIPHER_TRANSFORMATION = "AES/GCM/NoPadding"
    private const val CIPHER_IV = "iv"
    private const val CIPHER_ENCRYPTED = "encrypted"

    fun encrypt(decryptedBytes: ByteArray): HashMap<String, ByteArray>? {

        //Get the key
        generateKey()
        val secretKey = getKey()

        //Encrypt data
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val map = HashMap<String, ByteArray>()
        map[CIPHER_IV] = cipher.iv
        map[CIPHER_ENCRYPTED] = cipher.doFinal(decryptedBytes)

        return map
    }

    fun decrypt(map: HashMap<String, ByteArray>): ByteArray? {
        //Get the key
        val secretKey = getKey()

        //Decrypt data
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        val spec = GCMParameterSpec(128, map[CIPHER_IV])
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        return cipher.doFinal(map[CIPHER_ENCRYPTED])
    }

    private fun getKey(): SecretKey {
        val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)

        val secretKeyEntry = keyStore.getEntry(ALIAS, null) as KeyStore.SecretKeyEntry
        return secretKeyEntry.secretKey
    }

    private fun generateKey() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            generateKeyM()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun generateKeyM() {
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