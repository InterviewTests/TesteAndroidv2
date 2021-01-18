package com.jeanjnap.infrastructure.crypto

import android.content.Context
import android.util.Base64
import com.jeanjnap.infrastructure.crypto.key.RSAKey
import java.io.ByteArrayOutputStream
import javax.crypto.Cipher

class RSACryptoImpl(
    context: Context
): RSACrypto {

    private val rsaKey = RSAKey(context).apply {
        initAndGenerateKeyPair()
    }

    private val encryptCipher: Cipher by lazy {
        Cipher.getInstance(CIPHER_RSA_ENCRYPT_MODE).apply {
            init(Cipher.ENCRYPT_MODE, rsaKey.getPrivateKey())
        }
    }

    private val decryptCipher: Cipher by lazy {
        Cipher.getInstance(CIPHER_RSA_ENCRYPT_MODE).apply {
            init(Cipher.DECRYPT_MODE, rsaKey.getPublicKey())
        }
    }
    override fun encrypt(value: String): String {
        val originalMessage = value.toByteArray()
        return if (originalMessage.size > CIPHER_BLOCK_SIZE) {
            encryptLargeText(encryptCipher, originalMessage)
        } else {
            Base64.encodeToString(encryptCipher.doFinal(value.toByteArray()), Base64.DEFAULT)
        }
    }

    override fun decrypt(value: String): String {
        val encrypted = Base64.decode(value, Base64.DEFAULT)
        return if (encrypted.size > CIPHER_BLOCK_SIZE)
            decryptLargeText(decryptCipher, encrypted)
        else {
            String(decryptCipher.doFinal(Base64.decode(value, Base64.DEFAULT)))
        }
    }

    private fun encryptLargeText(cipher: Cipher, message: ByteArray): String {
        // k - 11 octets (k is the octet length of the RSA modulus) k -> KeySize/8
        var limit: Int = (RSAKey.RSA_KEY_SIZE / EIGHT_VALUE) - ELEVEN_VALUE
        var position = ZERO_VALUE

        val byteArrayOutputStream = ByteArrayOutputStream()
        while (position < message.size) {
            if (message.size - position < limit) limit = message.size - position
            val res = cipher.doFinal(message, position, limit)
            byteArrayOutputStream.write(res)
            position += limit
        }

        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
    }

    private fun decryptLargeText(cipher: Cipher, encryptedMessage: ByteArray): String {
        var limit = RSAKey.RSA_KEY_SIZE / EIGHT_VALUE
        var position = ZERO_VALUE

        val byteArrayOutputStream = ByteArrayOutputStream()
        while (position < encryptedMessage.size) {
            if (encryptedMessage.size - position < limit) {
                limit = encryptedMessage.size - position
            }
            val result = cipher.doFinal(encryptedMessage, position, limit)
            byteArrayOutputStream.write(result)
            position += limit
        }

        return String(byteArrayOutputStream.toByteArray())
    }

    companion object {
        private const val ZERO_VALUE = 0
        private const val EIGHT_VALUE = 8
        private const val ELEVEN_VALUE = 11
        const val CIPHER_RSA_ENCRYPT_MODE = "RSA/ECB/PKCS1Padding"
        const val CIPHER_BLOCK_SIZE = 256
    }
}