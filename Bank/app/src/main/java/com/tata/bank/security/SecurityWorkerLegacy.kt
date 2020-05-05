package com.tata.bank.security

import android.content.Context
import android.security.KeyPairGeneratorSpec
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.security.auth.x500.X500Principal

class SecurityWorkerLegacy(val context: Context) : ISecurityWorker {

    val ALIAS = "com.security"
    val KEYSTORE_PROVIDER = "AndroidKeyStore"
    val CIPHER_PROVIDER = "AndroidOpenSSL"
    val CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding"
    val ALGORITHM = "RSA"

    override fun encrypt(decryptedBytes: ByteArray): CipherData {
        generateKey()

        val publicKey = getKey()?.certificate?.publicKey as? RSAPublicKey
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION, CIPHER_PROVIDER)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val outputStream = ByteArrayOutputStream()
        val cipherOutputStream = CipherOutputStream(outputStream, cipher)
        cipherOutputStream.write(decryptedBytes)
        cipherOutputStream.close()

        return CipherData(outputStream.toByteArray())
    }

    override fun decrypt(encryptedData: CipherData): ByteArray? {

        val privateKey = getKey()?.privateKey as RSAPrivateKey

        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION, CIPHER_PROVIDER)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)

        val cipherInputStream = CipherInputStream(
            ByteArrayInputStream(encryptedData.encrypted),
            cipher)
        val arrayList = ArrayList<Byte>()
        var nextByte: Int?
        while (cipherInputStream.read().also { nextByte = it } != -1) {
            nextByte?.let {
                arrayList.add(it.toByte())
            }
        }

        val decryptedBytes = ByteArray(arrayList.size)
        for (i in decryptedBytes.indices) {
            decryptedBytes[i] = arrayList[i]
        }

        return decryptedBytes
    }

    private fun getKey(): KeyStore.PrivateKeyEntry? {
        val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)

        return keyStore.getEntry(ALIAS, null) as? KeyStore.PrivateKeyEntry
    }

    private fun generateKey() {
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
            KeyPairGenerator.getInstance(ALGORITHM, KEYSTORE_PROVIDER)
        generator.initialize(spec)
        generator.generateKeyPair()
    }
}