package br.com.crmm.bankapplication.util

import androidx.core.util.PatternsCompat

class ValidationUtil(
    private val cpfUtil: CPFUtil,
    private val passwordUtil: PasswordUtil
) {

    fun isValidUsername(text: String) = isValidCpf(text) || isValidEmail(text)

    fun isValidCpf(text: String) = cpfUtil.isValid(text)

    fun isValidEmail(text: String) = PatternsCompat.EMAIL_ADDRESS.matcher(text).matches()

    fun isValidPassword(password: String) = passwordUtil.isValid(password)

}