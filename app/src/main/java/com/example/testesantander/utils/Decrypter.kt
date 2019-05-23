package com.example.testesantander.utils

import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.*
import javax.crypto.spec.GCMParameterSpec

internal class Decrypter @Throws()
constructor() {

    private var keyStore: KeyStore? = null

    init {
        initKeyStore()
    }

    @Throws()
    private fun initKeyStore() {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore!!.load(null)
    }

    @Throws()
    fun decryptData(alias: String, encryptedData: ByteArray, encryptionIv: ByteArray): String {

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(128, encryptionIv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(alias), spec)

        return String(cipher.doFinal(encryptedData), charset("UTF-8"))
    }

    @Throws(NoSuchAlgorithmException::class, UnrecoverableEntryException::class, KeyStoreException::class)
    private fun getSecretKey(alias: String): SecretKey {
        return (keyStore!!.getEntry(alias, null) as KeyStore.SecretKeyEntry).secretKey
    }

    companion object {

        private val TRANSFORMATION = "AES/GCM/NoPadding"
        private val ANDROID_KEY_STORE = "AndroidKeyStore"
    }
}