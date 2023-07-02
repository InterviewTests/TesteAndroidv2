package com.nandoligeiro.safrando.domain.common.checkCPF

import javax.inject.Inject

class NewCheckCPFUseCaseImpl @Inject constructor() : CheckCPFUseCase {

    companion object {
        private const val CPF_LENGTH = 11
    }

    override fun invoke(value: String): Boolean {
        val formattedCPF = value.replace("[^0-9]".toRegex(), "")
        if (formattedCPF.length != CPF_LENGTH || formattedCPF.all { it == formattedCPF[0] }) {
            return false
        }
        val digits = formattedCPF.substring(0, 9).toCharArray()
        val verifierDigit1 = calculateVerifierDigit(digits, 10)
        val verifierDigit2 =
            calculateVerifierDigit(digits.plus(verifierDigit1.toString().toCharArray()), 11)
        return formattedCPF.substring(9, 11) == "$verifierDigit1$verifierDigit2"
    }

    private fun calculateVerifierDigit(digits: CharArray, multiplier: Int): Int {
        var sum = 0
        for (i in digits.indices) {
            sum += Character.getNumericValue(digits[i]) * (multiplier - i)
        }
        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }
}
