package com.example.testesantander.utils

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.InvalidAlgorithmParameterException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


internal class Encrypter {

    var iv: ByteArray? = null

    fun encryptText(alias: String, textToEncrypt: String): ByteArray {

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias))

        iv = cipher.iv

        return cipher.doFinal(textToEncrypt.toByteArray(charset("UTF-8")))
    }

    fun getIV(): ByteArray? {
        return iv
    }

    @Throws(NoSuchAlgorithmException::class, NoSuchProviderException::class, InvalidAlgorithmParameterException::class)
    private fun getSecretKey(alias: String): SecretKey {

        val keyGenerator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)

        } else {
            KeyGenerator.getInstance(ANDROID_KEY_STORE)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build()
            )
        }
        else{
            keyGenerator.init(alias.length, SecureRandom())
        }

        return keyGenerator.generateKey()
    }

    companion object {

        private val TRANSFORMATION = "AES/GCM/NoPadding"
        private val ANDROID_KEY_STORE = "AndroidKeyStore"
    }
}