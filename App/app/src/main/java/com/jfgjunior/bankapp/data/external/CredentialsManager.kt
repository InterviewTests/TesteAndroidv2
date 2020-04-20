package com.jfgjunior.bankapp.data.external

import android.content.SharedPreferences
import android.util.Base64
import com.jfgjunior.bankapp.data.models.UserCredentials
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class CredentialsManager(private val sharedPreferences: SharedPreferences) {

    fun saveCredentials(credentials: UserCredentials) {
        with(sharedPreferences.edit()) {
            putString(PASSWORD_KEY, encrypt(credentials.password))
            putString(USER_KEY, encrypt(credentials.user))
            apply()
        }
    }

    fun getCredentials(): UserCredentials? {
        if (sharedPreferences.contains(PASSWORD_KEY) && sharedPreferences.contains(USER_KEY)) {
            val password = sharedPreferences.getString(PASSWORD_KEY, "") ?: ""
            val user = sharedPreferences.getString(USER_KEY, "") ?: ""
            if (password.isEmpty() && user.isEmpty()) {
                return null
            }
            return UserCredentials(decrypt(user), decrypt(password))
        }
        return null
    }

    fun clearUser() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }

    private fun encrypt(valueToEnc: String): String {
        val key: Key = getKey()
        val c: Cipher = Cipher.getInstance(ALGORITHM)
        c.init(Cipher.ENCRYPT_MODE, key)
        val encValue: ByteArray = c.doFinal(valueToEnc.toByteArray())
        return String(Base64.encode(encValue, 0))
    }

    private fun decrypt(encryptedValue: String?): String {
        val key: Key = getKey()
        val c: Cipher = Cipher.getInstance(ALGORITHM)
        c.init(Cipher.DECRYPT_MODE, key)
        val decordedValue: ByteArray = Base64.decode(encryptedValue, 0)
        val decValue: ByteArray = c.doFinal(decordedValue)
        return String(decValue)
    }

    private fun getKey(): Key {
        // TODO: store this key on the keychain
        val keyValue = "qdmlcfhfabmizzpi".map { it.toByte() }.toByteArray()
        return SecretKeySpec(keyValue, ALGORITHM)
    }

    companion object {
        private const val ALGORITHM = "AES"
        private const val PASSWORD_KEY = "password"
        private const val USER_KEY = "user"
    }
}