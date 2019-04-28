package br.com.alex.bankappchallenge.utils

class BankAccountFormatter: BankAccountFormatterContract {
    override fun formatter(bankNumber: String, agencyNumber: String): String {
        return ""
    }
}

interface BankAccountFormatterContract {
    fun formatter(bankNumber: String, agencyNumber: String): String
}