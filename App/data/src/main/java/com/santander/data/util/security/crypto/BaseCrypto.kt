package com.santander.data.util.security.crypto

import android.content.Context
import android.util.Base64
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException

abstract class BaseCrypto(
    private val alias: String,
    val context: Context
) : CryptoManager {

    companion object {

        private val TAG = BaseCrypto::class.java.simpleName

        const val KEYSTORE_PROVIDER_ANDROID_KEYSTORE = "AndroidKeyStore"
        const val TYPE_RSA = "RSA"
        private const val PADDING_TYPE = "PKCS1Padding"
        private const val BLOCKING_MODE = "ECB"

        const val TRANSFORMATION = "$TYPE_RSA/$BLOCKING_MODE/$PADDING_TYPE"
    }

    private val cipher: Cipher
    private val keyStore: KeyStore

    init {
        cipher = this.createCipher()
        keyStore = this.createKeyStore()
        keyStore.load(null)
        createKeystoreAliasIfNeeded()
    }

    private fun createKeystoreAliasIfNeeded() {
        if (!keyStore.containsAlias(alias)) this.createNewKey(alias = alias)
    }

    @Throws(
        UnrecoverableKeyException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        InvalidKeyException::class,
        IOException::class
    )
    override fun encrypt(plainStr: String): String {
        createKeystoreAliasIfNeeded()
        val encryptKey = getEncryptKey(keyStore = keyStore, alias = alias)
        cipher.init(Cipher.ENCRYPT_MODE, encryptKey)
        return Base64.encodeToString(cipher.doFinal(plainStr.toByteArray()),Base64.DEFAULT)
    }

    @Throws(
        UnrecoverableKeyException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        IOException::class
    )
    override fun decrypt(encryptedStr: String): String {
        createKeystoreAliasIfNeeded()
        val decryptKey = getDecryptKey(keyStore = keyStore, alias = alias)
        cipher.init(Cipher.DECRYPT_MODE, decryptKey)
        return String(cipher.doFinal(Base64.decode(encryptedStr, Base64.DEFAULT)))
    }


    @Throws(KeyStoreException::class)
    override fun reset(): Boolean {
        val hasAlias = keyStore.containsAlias(alias)
        if (hasAlias) {
            keyStore.deleteEntry(alias)
        }
        return hasAlias
    }


    @Throws(
        KeyStoreException::class,
        CertificateException::class,
        NoSuchAlgorithmException::class,
        IOException::class,
        NoSuchProviderException::class,
        InvalidAlgorithmParameterException::class
    )
    protected abstract fun createKeyStore(): KeyStore


    @Throws(NoSuchPaddingException::class, NoSuchAlgorithmException::class)
    protected abstract fun createCipher(): Cipher


    @Throws(
        UnrecoverableKeyException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        InvalidKeyException::class,
        IOException::class
    )
    protected abstract fun getEncryptKey(keyStore: KeyStore, alias: String): Key


    @Throws(
        UnrecoverableKeyException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        IOException::class
    )
    protected abstract fun getDecryptKey(keyStore: KeyStore, alias: String): Key


    @Throws(
        NoSuchAlgorithmException::class,
        InvalidAlgorithmParameterException::class,
        NoSuchProviderException::class,
        KeyStoreException::class
    )
    protected abstract fun createNewKey(alias: String)

}
