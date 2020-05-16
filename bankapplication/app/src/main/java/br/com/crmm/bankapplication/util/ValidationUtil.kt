package br.com.crmm.bankapplication.util

import androidx.core.util.PatternsCompat

class ValidationUtil(
    private val cpfUtil: CPFUtil
) {

    fun isValidUsername(text: String) = isValidCpf(text) || isValidEmail(text)

    fun isValidCpf(text: String) = cpfUtil.isValid(text)

    fun isValidEmail(text: String) = PatternsCompat.EMAIL_ADDRESS.matcher(text).matches()

}