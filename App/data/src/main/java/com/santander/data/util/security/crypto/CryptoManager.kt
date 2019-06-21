package com.santander.data.util.security.crypto

import android.content.Context
import android.os.Build
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.NoSuchPaddingException

interface CryptoManager {

    @Throws(
        KeyStoreException::class,
        NoSuchPaddingException::class,
        NoSuchAlgorithmException::class,
        InvalidKeyException::class,
        IOException::class,
        NoSuchProviderException::class,
        InvalidAlgorithmParameterException::class,
        UnrecoverableEntryException::class
    )
    fun encrypt(plainStr: String): String

    @Throws(
        UnrecoverableKeyException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        IOException::class
    )
    fun decrypt(encryptedStr: String): String

    @Throws(KeyStoreException::class)
    fun reset(): Boolean

    class Builder(var alias: String, val context: Context) {

        @Throws(
            CertificateException::class,
            NoSuchAlgorithmException::class,
            KeyStoreException::class,
            NoSuchProviderException::class,
            InvalidAlgorithmParameterException::class,
            IOException::class,
            NoSuchPaddingException::class
        )
        fun build(): CryptoManager {
            return when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                    RSACryptoM(alias = alias, context = context)
                }
                Build.VERSION_CODES.JELLY_BEAN_MR2 <= Build.VERSION.SDK_INT -> {
                    RSACrypto(alias = alias, context = context)
                }
                else -> throw NoSuchAlgorithmException("RSA is no supported")
            }
        }
    }

}
