package com.tata.bank.security

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.*
import javax.crypto.spec.GCMParameterSpec
import javax.security.auth.x500.X500Principal

class SecurityWorker(val context: Context) {

    private val ALIAS = "com.security"
    private val KEYSTORE_PROVIDER = "AndroidKeyStore"
    private val CIPHER_TRANSFORMATION = "AES/GCM/NoPadding"

    fun encrypt(decryptedBytes: ByteArray): CipherData? {

        //Get the key
        generateKey()
        val secretKey = getKeyAboveM()

        //Encrypt data
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val encrypted = cipher.doFinal(decryptedBytes)
        return CipherData(encrypted, cipher.iv)
    }

    fun decrypt(cipherData: CipherData): ByteArray? {
        //Get the key
        val secretKey = getKeyAboveM()

        //Decrypt data
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        val spec = GCMParameterSpec(128, cipherData.iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        return cipher.doFinal(cipherData.encrypted)
    }

    private fun getKeyPreM(): PrivateKey? {
        val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)

        val key = keyStore.getEntry(ALIAS, null) as? KeyStore.PrivateKeyEntry
        return key?.privateKey
    }

    private fun getKeyAboveM(): SecretKey? {
        val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)

        val key = keyStore.getEntry(ALIAS, null) as? KeyStore.SecretKeyEntry
        return key?.secretKey
    }

    private fun generateKey() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            generateKeyAboveM()
        } else {
            generateKeyPreM()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun generateKeyAboveM() {
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

    private fun generateKeyPreM() {
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)

        val start = Calendar.getInstance()
        val end = Calendar.getInstance()

        end.add(Calendar.YEAR, 10)

        val spec = KeyPairGeneratorSpec.Builder(context)
            .setAlias(ALIAS)
            .setSubject(X500Principal("CN=$ALIAS, O=Android Authority"))
            .setSerialNumber(BigInteger(1024, Random()))
            .setStartDate(start.time)
            .setEndDate(end.time)
//            .setEncryptionRequired() // set this to require PIN on device
            .build()
        val generator =
            KeyPairGenerator.getInstance("RSA", KEYSTORE_PROVIDER)
        generator.initialize(spec)
        generator.generateKeyPair()
    }

    fun rsaEncrypt(decryptedBytes: ByteArray?): ByteArray? {
        var encryptedBytes: ByteArray? = null
        try {
            val keyStore =
                KeyStore.getInstance(KEYSTORE_PROVIDER)
            keyStore.load(null)
            val privateKeyEntry =
                keyStore.getEntry(ALIAS, null) as KeyStore.PrivateKeyEntry
            val publicKey: RSAPublicKey =
                privateKeyEntry.certificate.publicKey as RSAPublicKey
            val cipher =
                Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)
            val outputStream = ByteArrayOutputStream()
            val cipherOutputStream = CipherOutputStream(outputStream, cipher)
            cipherOutputStream.write(decryptedBytes)
            cipherOutputStream.close()
            encryptedBytes = outputStream.toByteArray()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return encryptedBytes
    }

    fun rsaDecrypt(encryptedBytes: ByteArray?): ByteArray? {
        var decryptedBytes: ByteArray? = null
        try {
            val keyStore =
                KeyStore.getInstance(KEYSTORE_PROVIDER)
            keyStore.load(null)
            val privateKeyEntry =
                keyStore.getEntry(ALIAS, null) as KeyStore.PrivateKeyEntry
            val privateKey: RSAPrivateKey = privateKeyEntry.privateKey as RSAPrivateKey
            val cipher =
                Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL")
            cipher.init(Cipher.DECRYPT_MODE, privateKey)
            val cipherInputStream =
                CipherInputStream(ByteArrayInputStream(encryptedBytes), cipher)
            val arrayList: ArrayList<Byte> = ArrayList()
            var nextByte: Int?
            while (cipherInputStream.read().also { nextByte = it } != -1) {
                nextByte?.let {
                    arrayList.add(it.toByte())
                }
            }
            decryptedBytes = ByteArray(arrayList.size)
            for (i in decryptedBytes.indices) {
                decryptedBytes[i] = arrayList[i]
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return decryptedBytes
    }
}