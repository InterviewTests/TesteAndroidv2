package pt.felipegouveia.bankapp.util.security

import android.util.Base64
import pt.felipegouveia.testing.OpenForTesting
import java.security.Key
import javax.crypto.Cipher

/**
 * This class wraps [Cipher] class apis with some additional possibilities.
 */
class CipherWrapper(private val transformation: String) {

    private val cipher: Cipher = Cipher.getInstance(transformation)

    fun encrypt(data: String, key: Key?): String {
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val bytes = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    fun decrypt(data: String?, key: Key?): String {
        return if(data != null) {
            cipher.init(Cipher.DECRYPT_MODE, key)
            val encryptedData = Base64.decode(data, Base64.DEFAULT)
            val decodedData = cipher.doFinal(encryptedData)
           String(decodedData)
        } else {
           ""
        }
    }

    companion object {
        var TRANSFORMATION_ASYMMETRIC = "RSA/ECB/PKCS1Padding"
    }
}