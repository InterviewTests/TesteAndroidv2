package com.felipemsa.idletime.data

import com.felipemsa.idletime.mask

data class LoginResponse(var userAccount: UserAccount, var error: Error)

data class UserAccount(var userId: Int, var name: String, var bankAccount: String, var agency: String, var balance: Double) {
    fun formatedAccountWithAgency(): String {
        val account = bankAccount + agency
        return account.mask("#### / ##.######-#")
    }
}

data class StatementsResponse(var statementList: List<Statement>)

data class Statement(var title: String, var desc: String, var date: String, var value: Double)

data class Error(var code: Int, var message: String)