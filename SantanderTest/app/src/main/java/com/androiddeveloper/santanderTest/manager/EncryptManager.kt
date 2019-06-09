package com.androiddeveloper.santanderTest.manager

import android.util.Base64
import android.util.Log
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import java.io.*
import java.nio.ByteBuffer
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptManager {

    private val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    private val key = ByteArray(16)

    fun encrypt(data: Data): String {
        val encrypted = secureEncrypt(data)
        return String(Base64.encode(encrypted, Base64.DEFAULT), charset("UTF-8"))
    }

    fun encryptLastUser(username: String): String {
        val encrypted = secureEncryptUsername(username)
        return String(Base64.encode(encrypted, Base64.DEFAULT), charset("UTF-8"))
    }

    fun decrypt(encryptedData: String): Data? {
        val decrypted = Base64.decode(encryptedData, Base64.DEFAULT)
        return secureDecrypt(decrypted)
    }

    fun decryptUsername(encryptedData: String): String? {
        val decrypted = Base64.decode(encryptedData, Base64.DEFAULT)
        return secureDecryptUsername(decrypted)
    }

    private fun secureEncrypt(data: Data): ByteArray? {
        val secureRandom = SecureRandom()
        try {
            secureRandom.nextBytes(key)
            val secretKey = SecretKeySpec(key, "AES")

            val parameterSpec = GCMParameterSpec(128, key)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec)

            val encrypted = cipher.doFinal(serialization(data))

            val byteBuffer = ByteBuffer.allocate(4 + key.size + encrypted.size)
            byteBuffer.putInt(key.size)
            byteBuffer.put(key)
            byteBuffer.put(encrypted)
            return byteBuffer.array()
        } catch (e: Exception) {
            Log.e("Exception", e.message)
        }
        return null
    }

    private fun secureDecrypt(encryptedData: ByteArray): Data? {
        val iv: ByteArray?
        val encrypted: ByteArray?
        try {
            val byteBuffer = ByteBuffer.wrap(encryptedData)

            val ivLength = byteBuffer.int
            iv = ByteArray(ivLength)
            byteBuffer.get(iv)
            encrypted = ByteArray(byteBuffer.remaining())
            byteBuffer.get(encrypted)

            cipher.init(
                Cipher.DECRYPT_MODE,
                SecretKeySpec(iv, "AES"),
                GCMParameterSpec(128, iv)
            )

            val encryptedText = cipher.doFinal(encrypted)
            return deserialization(encryptedText)
        } catch (e: Exception) {
            Log.e("Exception", e.message)
        }

        return null
    }

    private fun serialization(data: Data): ByteArray? {
        val baos = ByteArrayOutputStream()
        try {
            val out = ObjectOutputStream(baos)
            out.writeObject(data)
            out.flush()
            return baos.toByteArray()
        } catch (e: IOException) {
            Log.e("ioErrorEncrpt", e.message)
        } finally {
            baos.close()
        }

        return null
    }

    private fun deserialization(encrypted: ByteArray): Data? {
        val bais = ByteArrayInputStream(encrypted)
        try {
            val input = ObjectInputStream(bais)
            return input.readObject() as Data
        } catch (e: IOException) {

        } finally {
            bais.close()
        }
        return null
    }

    private fun secureEncryptUsername(username: String): ByteArray? {
        val secureRandom = SecureRandom()
        try {
            secureRandom.nextBytes(key)
            val secretKey = SecretKeySpec(key, "AES")

            val parameterSpec = GCMParameterSpec(128, key)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec)

            val encrypted = cipher.doFinal(username.toByteArray(charset("UTF-8")))

            val byteBuffer = ByteBuffer.allocate(4 + key.size + encrypted.size)
            byteBuffer.putInt(key.size)
            byteBuffer.put(key)
            byteBuffer.put(encrypted)
            return byteBuffer.array()
        } catch (e: Exception) {
            Log.e("Exception", e.message)
        }
        return null
    }

    private fun secureDecryptUsername(encryptedData: ByteArray): String? {
        val iv: ByteArray?
        val encrypted: ByteArray?
        try {
            val byteBuffer = ByteBuffer.wrap(encryptedData)

            val ivLength = byteBuffer.int
            iv = ByteArray(ivLength)
            byteBuffer.get(iv)
            encrypted = ByteArray(byteBuffer.remaining())
            byteBuffer.get(encrypted)

            cipher.init(
                Cipher.DECRYPT_MODE,
                SecretKeySpec(iv, "AES"),
                GCMParameterSpec(128, iv)
            )

            val encryptedText = cipher.doFinal(encrypted)
            return String(encryptedText, charset("UTF-8"))
        } catch (e: Exception) {
            Log.e("Exception", e.message)
        }

        return null
    }
}