package com.felipemsa.idletime.data

data class LoginResponse(var userAccount: UserAccount, var error: Error)

data class UserAccount(var userId: Int, var name: String, var bankAccount: String, var agency: String, var balance: Double)

data class StatementsResponse(var statementList: List<Statement>)

data class Statement(var title: String, var desc: String, var date: String, var value: Double)

data class Error(var code: Int, var message: String)