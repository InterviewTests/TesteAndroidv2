package br.com.crmm.bankapplication.util

import android.util.Patterns

class ValidationUtil(
    private val cpfUtil: CPFUtil
) {

    fun isValidUsername(text: String) = isValidCpf(text) || isValidEmail(text)

    private fun isValidCpf(text: String) = cpfUtil.isValid(text)

    private fun isValidEmail(text: String) = Patterns.EMAIL_ADDRESS.matcher(text).matches()

}