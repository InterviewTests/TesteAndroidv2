package com.santander.data.util.security.crypto

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException

@TargetApi(Build.VERSION_CODES.M)
class RSACryptoM(
    alias: String,
    context: Context
) : BaseCrypto(
    alias = alias,
    context = context
) {

    @Throws(
        KeyStoreException::class,
        CertificateException::class,
        NoSuchAlgorithmException::class,
        IOException::class,
        NoSuchProviderException::class,
        InvalidAlgorithmParameterException::class
    )
    override fun createKeyStore(): KeyStore {
        return KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
    }

    @Throws(NoSuchPaddingException::class, NoSuchAlgorithmException::class)
    override fun createCipher(): Cipher {
        return Cipher.getInstance(TRANSFORMATION)
    }

    @Throws(
        UnrecoverableKeyException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        InvalidKeyException::class,
        IOException::class
    )
    override fun getEncryptKey(keyStore: KeyStore, alias: String): Key {
        return keyStore.getCertificate(alias).publicKey
    }

    @Throws(
        UnrecoverableKeyException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        IOException::class
    )
    override fun getDecryptKey(keyStore: KeyStore, alias: String): Key {
        return keyStore.getKey(alias, null) as PrivateKey
    }

    @Throws(NoSuchProviderException::class, NoSuchAlgorithmException::class, InvalidAlgorithmParameterException::class)
    override fun createNewKey(alias: String) {
        val generator =
            KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
        generator.initialize(
            KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .build()
        )
        generator.generateKeyPair()
    }

}
