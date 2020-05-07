package br.com.raphael.everis.extensions

import android.util.Patterns
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Base64
import java.lang.Exception
import java.security.Security
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

fun String?.isValidCPF(): Boolean {
    val pesoCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
    fun calcularDigito(str: String, peso: IntArray): Int {
        var deslocamentoPeso = 0
        var indice = str.length - 1
        var digito: Int
        while (indice >= 0) {
            digito = Integer.parseInt(str.substring(indice, indice + 1))
            deslocamentoPeso += digito * peso[peso.size - str.length + indice]
            indice--
        }
        deslocamentoPeso = 11 - deslocamentoPeso % 11
        return if (deslocamentoPeso > 9) 0 else deslocamentoPeso
    }
    if (this == null) return false
    val cpf = this.replace(".", "").replace("-","")
    if (cpf.length != 11) return false
    val digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF)
    val digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF)
    return cpf == cpf.substring(0, 9) + digito1.toString() + digito2.toString()
}

fun String?.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this ?: "").matches()

fun String?.isNumeric(): Boolean {
    if (this == null) return false

    return try {
        val value = this.replace(".", "").replace("-","")
        value.toDouble()
        true
    } catch (e: Exception) {
        false
    }
}

fun String?.isValidPassword(): Boolean {
    if (this == null || this.length < 3) return false

    val str = this
    var valid = true

    // A senha deve conter pelo menos um número ou letra minúscula
    var exp = ".*[a-z0-9].*"
    var pattern = Pattern.compile(exp)
    var matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    // A senha deve conter pelo menos uma letra maiúscula
    exp = ".*[A-Z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    // A senha deve conter pelo menos um caractere especial
    // Caracteres especiais permitidos : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
    exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(str)
    if (!matcher.matches()) {
        valid = false
    }

    return valid
}

val key = byteArrayOf(36, 36, 32, 65, 64, 65, 38, 31, 36, 39, 38, 38, 65, 35, 38, 66, 62, 36, 64, 30, 35, 37, 64, 39, 64, 38, 35, 36, 30, 35, 65, 30)
fun String?.encrypt(): String {
    if (this == null) return ""

    Security.addProvider(BouncyCastleProvider())
    try {
        val skey = SecretKeySpec(key, "AES")
        val input = this.toByteArray(charset("UTF8"))

        synchronized(Cipher::class.java) {
            val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
            cipher.init(Cipher.ENCRYPT_MODE, skey)

            val cipherText = ByteArray(cipher.getOutputSize(input.size))
            var ctLength = cipher.update(
                input, 0, input.size,
                cipherText, 0
            )
            ctLength += cipher.doFinal(cipherText, ctLength)
            return String(
                Base64.encode(cipherText)
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}

fun String?.decrypt(): String {
    if (this == null) return ""

    Security.addProvider(BouncyCastleProvider())
    try {
        val skey = SecretKeySpec(key, "AES")
        val input = Base64
            .decode(this.trim { it <= ' ' }.toByteArray(charset("UTF8")))

        synchronized(Cipher::class.java) {
            val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
            cipher.init(Cipher.DECRYPT_MODE, skey)

            val plainText = ByteArray(cipher.getOutputSize(input.size))
            var ptLength = cipher.update(input, 0, input.size, plainText, 0)
            ptLength += cipher.doFinal(plainText, ptLength)
            val decryptedString = String(plainText)
            return decryptedString.trim { it <= ' ' }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return ""
}