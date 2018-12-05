package com.br.natanfelipe.bankapp.utils

import android.util.Log
import java.nio.charset.Charset

object EncripUtils {

    private val CHAVE_PADRAO = "BankApp"

    @Throws(Exception::class)
    fun encrypt(textToEncrypt: String, chave: String?): String {
        var stringEncrypt = ""

        val criptoDES = CriptoDES(chave ?: CHAVE_PADRAO)

        val bytesBase64 = textToEncrypt.toByteArray()

        val bytesEncrypt = criptoDES.encrypt(bytesBase64)

        if (bytesEncrypt != null)
            stringEncrypt = String(Base64.encodeBytesToBytes(bytesEncrypt), Charset.forName("UTF-8"))

        return stringEncrypt
    }

    fun encryptToAES(textToEncrypt: String, chave: String?): String {
        var stringEncrypt = ""
        try {
            if (textToEncrypt != "")
                stringEncrypt = CriptoDES.Encrypt(textToEncrypt, chave ?: CHAVE_PADRAO)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stringEncrypt
    }

    @Throws(Exception::class)
    fun decrypt(textToDecrypt: String, chave: String?): String {
        var stringDecrypt = ""

        val criptoDES = CriptoDES(chave ?: CHAVE_PADRAO)

        val bytesBase64 = textToDecrypt.toByteArray()

        val bytesDecrypt = criptoDES.decrypt(Base64.decode(bytesBase64))

        if (bytesDecrypt != null)
            stringDecrypt = String(bytesDecrypt, Charset.forName("UTF-8"))
        if (stringDecrypt.isEmpty())
            Log.i("EncripUtil", "SENHA DECRIPTADA ESTA VAZIA")

        return stringDecrypt
    }
}