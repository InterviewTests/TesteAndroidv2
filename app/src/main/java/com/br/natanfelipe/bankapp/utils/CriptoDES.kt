package com.br.natanfelipe.bankapp.utils

import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CriptoDES(key: String) {

    private val encryptKey: ByteArray

    init {
        this.encryptKey = prepareKey(key)
    }

    private fun prepareKey(key: String): ByteArray {

        val keyBytes = ByteArray(32)

        for (i in 0 until key.toByteArray().size) {
            keyBytes[i] = key.toByteArray()[i]
        }

        var j = 0
        var k = 16
        while (j < 8) {
            keyBytes[k++] = keyBytes[j++]
        }

        return keyBytes
    }

    fun encrypt(`in`: ByteArray): ByteArray? {

        var encrypted: ByteArray? = null

        try {
            val mode = Cipher.ENCRYPT_MODE

            val cipher = getCipher(mode)

            encrypted = cipher.doFinal(`in`)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return encrypted
    }

    fun decrypt(`in`: ByteArray): ByteArray? {

        var decrypted: ByteArray? = null

        try {
            val mode = Cipher.DECRYPT_MODE

            val cipher = getCipher(mode)

            decrypted = cipher.doFinal(`in`)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return decrypted
    }

    @Throws(NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class, InvalidKeySpecException::class)
    private fun getCipher(mode: Int): Cipher {

        val cipher = javax.crypto.Cipher.getInstance("AES")
        val keySpec = DESedeKeySpec(encryptKey)
        val secretKeyFactory = SecretKeyFactory.getInstance("DESede")
        val secretKey = secretKeyFactory.generateSecret(keySpec)

        cipher.init(mode, secretKey)
        return cipher
    }

    companion object {

        @Throws(Exception::class)
        fun Encrypt(text: String, key: String): String {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val keyBytes = ByteArray(32)
            val b = key.toByteArray(charset("UTF-8"))

            var len = b.size
            if (len > keyBytes.size) len = keyBytes.size
            System.arraycopy(b, 0, keyBytes, 0, len)
            val keySpec = SecretKeySpec(keyBytes, "AES")
            val ivSpec = IvParameterSpec(ByteArray(16))
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

            val results = cipher.doFinal(text.toByteArray(charset("UTF-8")))
            return Base64.encodeBytes(results)
        }
    }

}