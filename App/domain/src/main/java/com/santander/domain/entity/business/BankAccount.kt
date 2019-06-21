package com.santander.domain.entity.business

import com.santander.domain.exception.InvalidAccountException

data class BankAccount(
    val agency: String,
    val account: String
) {
    private fun agencyFormatted() = "${agency.substring(0, 2)}.${agency.substring(2, 7)}-${agency.substring(7, 9)}"
        .takeIf { agency.length == 9 } ?: throw InvalidAccountException()

    fun agencyAndAccountFormatted() = "$account / ${agencyFormatted()}"
}