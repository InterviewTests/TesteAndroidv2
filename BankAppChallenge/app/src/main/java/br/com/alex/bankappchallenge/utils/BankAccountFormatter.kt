package br.com.alex.bankappchallenge.utils

class BankAccountFormatter : BankAccountFormatterContract {
    override fun formatter(bankNumber: String, agencyNumber: String): String {
        return "$bankNumber / ${agencyNumber.subSequence(0, 2)}.${agencyNumber.subSequence(2, 8)}-${agencyNumber.last()}"
    }
}

interface BankAccountFormatterContract {
    fun formatter(bankNumber: String, agencyNumber: String): String
}